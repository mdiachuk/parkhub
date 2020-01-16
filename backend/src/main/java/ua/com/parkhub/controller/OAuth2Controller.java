package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.dto.PhoneEmailDTO;

import ua.com.parkhub.mappers.dtoToModel.AuthUserDTOtoAuthUserModelMapper;
import ua.com.parkhub.mappers.dtoToModel.PhoneEmailDTOtoPhoneEmailMapper;
import ua.com.parkhub.model.AuthUserModel;
import ua.com.parkhub.model.PhoneEmailModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.service.ISignUpService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.LinkedHashMap;


@RestController
public class OAuth2Controller {

    @Value("${frontUrl}")
    private String frontUrl;

    private final ISignUpService signUpService;
    private JwtUtil jwtUtil;
    private AuthUserDTOtoAuthUserModelMapper authUserDTOtoAuthUserModelMapper;
    private PhoneEmailDTOtoPhoneEmailMapper phoneEmailDTOtoPhoneEmailMapper;


    @Autowired
    public OAuth2Controller(ISignUpService signUpService, AuthUserDTOtoAuthUserModelMapper Mapper, PhoneEmailDTOtoPhoneEmailMapper phoneEmailDTOtoPhoneEmailMapper,JwtUtil jwtUtil) {
        this.signUpService = signUpService;
        this.authUserDTOtoAuthUserModelMapper = Mapper;
        this.phoneEmailDTOtoPhoneEmailMapper = phoneEmailDTOtoPhoneEmailMapper;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping("/api/oauthSuccess")
    public void setAuthUser(HttpServletResponse response,OAuth2Authentication user) throws IOException {
        LinkedHashMap<String,String> authMap = (LinkedHashMap<String,String>) user.getUserAuthentication().getDetails();
        String firstName = authMap.get("given_name");
        String lastName = authMap.get("family_name");
        String email = authMap.get("email");
        AuthUserDTO authUser = new AuthUserDTO();
        authUser.setFirstName(firstName);
        authUser.setLastName(lastName);
        authUser.setEmail(email);
        AuthUserModel model = authUserDTOtoAuthUserModelMapper.transform(authUser);
        if (!signUpService.isUserPresentByEmail(model.getEmail())){
            signUpService.createUserAfterSocialAuth(model);
            response.sendRedirect(frontUrl+"/phone-number?email="+email);
        }
        else if(signUpService.isCustomerNumberEmpty(email)){
            response.sendRedirect(frontUrl+"/phone-number?email="+email);
        }
        else{
            UserModel userModel = signUpService.findUserbyEmail(email);
            String token = jwtUtil.generateToken(userModel.getEmail(),userModel.getRole().getRoleName(), userModel.getId());
            String value = "TOKEN=";
            String value1 = value + token;
            String value2 =value1 +"; Max-Age=604800; Path=/";
            response.setHeader("Set-Cookie",value2);
            response.sendRedirect(frontUrl+"/home");
        }

    }


    @PutMapping("/api/customer")
    public ResponseEntity updatePhone(@RequestBody PhoneEmailDTO phoneEmailDTO) {
        if (!signUpService.isNumberUnique(phoneEmailDTO.getPhoneNumber())){
            return new ResponseEntity("This phone number is already used", HttpStatus.BAD_REQUEST);
        }
        PhoneEmailModel phoneEmailModel=phoneEmailDTOtoPhoneEmailMapper.transform(phoneEmailDTO);
        signUpService.setPhoneNumberForAuthUser(phoneEmailModel);
        return ResponseEntity.ok().build();

    }



}
