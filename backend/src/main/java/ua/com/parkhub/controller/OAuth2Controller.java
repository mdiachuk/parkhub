package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.dto.PhoneEmailDTO;
import ua.com.parkhub.dto.UserDTO;

import ua.com.parkhub.mappers.dtoToModel.AuthUserDTOtoAuthUserModelMapper;
import ua.com.parkhub.mappers.dtoToModel.PhoneEmailDTOtoPhoneEmailMapper;
import ua.com.parkhub.model.AuthUserModel;
import ua.com.parkhub.model.PhoneEmailModel;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.service.impl.SignUpService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.LinkedHashMap;

@RestController
public class OAuth2Controller {

    @Value("${frontUrl}")
    private String frontUrl;

    private final SignUpService  signUpService;
    private JwtUtil jwtUtil;
    private AuthUserDTOtoAuthUserModelMapper authUserDTOtoAuthUserModelMapper;
    private PhoneEmailDTOtoPhoneEmailMapper phoneEmailDTOtoPhoneEmailMapper;


    @Autowired
    public OAuth2Controller(SignUpService signUpService, AuthUserDTOtoAuthUserModelMapper Mapper, PhoneEmailDTOtoPhoneEmailMapper phoneEmailDTOtoPhoneEmailMapper,  JwtUtil jwtUtil) {
        this.signUpService = signUpService;
        this.authUserDTOtoAuthUserModelMapper = Mapper;
        this.phoneEmailDTOtoPhoneEmailMapper = phoneEmailDTOtoPhoneEmailMapper;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping("/oauthSuccess")
    public void handleFoo(HttpServletResponse response,OAuth2Authentication user) throws IOException {
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
            response.sendRedirect(frontUrl+"/home");
        }
            //TODO create cron for deleted unused cosial accounts
        //TODO : add jwt
    }


    @PutMapping("/customer")
    public void updatePhone(@RequestBody PhoneEmailDTO phoneEmailDTO) {
        PhoneEmailModel phoneEmailModel=phoneEmailDTOtoPhoneEmailMapper.transform(phoneEmailDTO);
        signUpService.setPhoneNumberForAuthUser(phoneEmailModel);

    }



}
