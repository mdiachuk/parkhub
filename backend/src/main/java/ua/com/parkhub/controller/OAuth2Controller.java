package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.dto.PhoneEmailDTO;

import ua.com.parkhub.dto.TokenDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.PhoneEmailModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.service.ISignUpService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class OAuth2Controller {

    @Value("${frontUrl}")
    private String frontUrl;

    private final ISignUpService signUpService;
    private Mapper<AuthUserDTO, UserModel> authUserDTOtoUserModelMapper;
    private Mapper<PhoneEmailDTO, PhoneEmailModel> phoneEmailDTOtoPhoneEmailMapper;


    @Autowired
    public OAuth2Controller(ISignUpService signUpService,
                            Mapper<AuthUserDTO,UserModel> authUserDTOtoUserModelMapper,
                            Mapper<PhoneEmailDTO, PhoneEmailModel> phoneEmailDTOtoPhoneEmailMapper) {
        this.signUpService = signUpService;
        this.authUserDTOtoUserModelMapper = authUserDTOtoUserModelMapper;
        this.phoneEmailDTOtoPhoneEmailMapper = phoneEmailDTOtoPhoneEmailMapper;
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
        UserModel model = authUserDTOtoUserModelMapper.transform(authUser);
        if (!signUpService.isUserPresentByEmail(model.getEmail())){
            signUpService.createUserAfterSocialAuth(model);
            response.sendRedirect(frontUrl+"/phone-number?email="+email);
        }
        else if(signUpService.isCustomerNumberEmpty(model.getEmail())){
            response.sendRedirect(frontUrl+"/phone-number?email="+email);
        }
        else{
            String tokenCookie = "TOKEN=" + signUpService.generateTokenForOauthUser(email) +"; Max-Age=604800; Path=/";
            response.setHeader("Set-Cookie",tokenCookie);
            response.sendRedirect(frontUrl+"/home");
        }

    }


    @PutMapping("/api/customer")
    public ResponseEntity<TokenDTO> updatePhone(@Valid @RequestBody PhoneEmailDTO phoneEmailDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        if (!signUpService.isNumberUnique(phoneEmailDTO.getPhoneNumber())){
            return new ResponseEntity("This phone number is already used", HttpStatus.BAD_REQUEST);
        }
        PhoneEmailModel phoneEmailModel=phoneEmailDTOtoPhoneEmailMapper.transform(phoneEmailDTO);
        signUpService.setPhoneNumberForAuthUser(phoneEmailModel);
        String jwtToken = signUpService.generateTokenForOauthUser(phoneEmailDTO.getEmail());
        TokenDTO token = new TokenDTO();
        token.setToken(jwtToken);
        return ResponseEntity.ok(token);

    }



}
