package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.dto.PhoneEmailDTO;
import ua.com.parkhub.mappers.AuthUserDTOtoAuthUserModelMapper;
import ua.com.parkhub.mappers.PhoneEmailDTOtoPhoneEmailMapper;
import ua.com.parkhub.model.AuthUserModel;
import ua.com.parkhub.model.PhoneEmailModel;
import ua.com.parkhub.service.impl.SignUpService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.LinkedHashMap;

@RestController
public class OAuth2Controller {

    @Value("${frontUrl}")
    private String frontUrl;

    private final SignUpService  signUpService;
    private AuthUserDTOtoAuthUserModelMapper authUserDTOtoAuthUserModelMapper;
    private PhoneEmailDTOtoPhoneEmailMapper phoneEmailDTOtoPhoneEmailMapper;


    @Autowired
    public OAuth2Controller(SignUpService signUpService, AuthUserDTOtoAuthUserModelMapper Mapper, PhoneEmailDTOtoPhoneEmailMapper phoneEmailDTOtoPhoneEmailMapper) {
        this.signUpService = signUpService;
        this.authUserDTOtoAuthUserModelMapper = Mapper;
        this.phoneEmailDTOtoPhoneEmailMapper = phoneEmailDTOtoPhoneEmailMapper;
    }
    //TODO:After user cabinet is made ,change link

//    @GetMapping("/home2")
//    public void user(OAuth2Authentication user) {
//        LinkedHashMap<String,String> authMap = (LinkedHashMap<String,String>) user.getUserAuthentication().getDetails();
//        String firstName = authMap.get("given_name");
//        String lastName = authMap.get("family_name");
//        String email = authMap.get("email");
//        AuthUserDTO authUser = new AuthUserDTO();
//        authUser.setFirstName(firstName);
//        authUser.setLastName(lastName);
//        authUser.setEmail(email);
//        AuthUserModel model = authUserDTOtoAuthUserModelMapper.transform(authUser);
//        signUpService.createUserAfterSocialAuth(model);
//    }
//
//    @GetMapping("/home3")
//    public ResponseEntity<Object> redirectToExternalUrl(OAuth2Authentication user) throws URISyntaxException {
//        URI yahoo = new URI("http://localhost:4200/manager/cabinet/123");
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(yahoo);
//
//        LinkedHashMap<String,String> authMap = (LinkedHashMap<String,String>) user.getUserAuthentication().getDetails();
//        String firstName = authMap.get("given_name");
//        String lastName = authMap.get("family_name");
//        String email = authMap.get("email");
//        AuthUserDTO authUser = new AuthUserDTO();
//        authUser.setFirstName(firstName);
//        authUser.setLastName(lastName);
//        authUser.setEmail(email);
//        AuthUserModel model = authUserDTOtoAuthUserModelMapper.transform(authUser);
//
//        return new ResponseEntity<>("model",httpHeaders, HttpStatus.SEE_OTHER);
//    }

    @RequestMapping("/home1")
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
            //check if cust number!=0 if it is null then go 84 85 else пропустить
            // if exists customers phone number  only redirect to home page
            // if customer phone number empty redirect to page for create phone number
            // TODO create cron for deleted unused cosial accounts


    }

    @PutMapping("/customer")
    public void updatePhone(@RequestBody PhoneEmailDTO phoneEmailDTO) {
        PhoneEmailModel phoneEmailModel=phoneEmailDTOtoPhoneEmailMapper.transform(phoneEmailDTO);
        signUpService.setPhoneNumberForAuthUser(phoneEmailModel);
    }

//    @PostMapping("/u")
//    public ResponseEntity<Void> getone() {
//        //PhoneEmailModel phoneEmailModel=phoneEmailDTOtoPhoneEmailMapper.transform(phoneEmailDTO);
//        //signUpService.setPhoneNumberForAuthUser(phoneEmailModel);
//        int i=2;
//        return new ResponseEntity<Void>();
//    }

}
