package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.mappers.AuthUserDTOtoAuthUserModelMapper;
import ua.com.parkhub.model.AuthUserModel;
import ua.com.parkhub.service.impl.SignUpService;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class OAuth2Controller {
    private final SignUpService  signUpService;
    private AuthUserDTOtoAuthUserModelMapper authUserDTOtoAuthUserModelMapper;

    @Autowired
    public OAuth2Controller(SignUpService signUpService, AuthUserDTOtoAuthUserModelMapper Mapper) {

        this.signUpService = signUpService;
        this.authUserDTOtoAuthUserModelMapper = Mapper;
    }
    //TODO:After user cabinet is made ,change link

    @RequestMapping("/home1")
    public void user(OAuth2Authentication user) {
        LinkedHashMap<String,String> authMap = (LinkedHashMap<String,String>) user.getUserAuthentication().getDetails();
        String firstName = authMap.get("given_name");
        String lastName = authMap.get("family_name");
        String email = authMap.get("email");
        AuthUserDTO authUser = new AuthUserDTO();
        authUser.setFirstName(firstName);
        authUser.setLastName(lastName);
        authUser.setEmail(email);
        AuthUserModel model = authUserDTOtoAuthUserModelMapper.transform(authUser);
        signUpService.createUserAfterSocialAuth(model);
    }
}
