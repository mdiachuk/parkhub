package ua.com.parkhub.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.dto.UserInfoDTO;

import ua.com.parkhub.exceptions.PasswordException;

import ua.com.parkhub.mappers.dtoToModel.PasswordDTOtoUserModelMapper;
import ua.com.parkhub.mappers.dtoToModel.UserDtoToUserModelMapper;
import ua.com.parkhub.mappers.dtoToModel.UserInfoDTOtoUserModelMapper;
import ua.com.parkhub.mappers.modelToDto.UserModelToUserInfoDTOMapper;
import ua.com.parkhub.service.impl.UserService;



@RestController
public class UserController {

    UserService userService;
    UserModelToUserInfoDTOMapper userModelToUserInfoDTOMapper;
    UserDtoToUserModelMapper userDTOtoModelMapper;
    PasswordDTOtoUserModelMapper passwordDTOtoUserModelMapper;
    UserInfoDTOtoUserModelMapper userInfoDTOtoUserModelMapper;

    @Autowired
    public UserController(UserService userService, UserModelToUserInfoDTOMapper userModelToUserInfoDTOMapper,
                          UserDtoToUserModelMapper userDTOtoModelMapper,
                          PasswordDTOtoUserModelMapper passwordDTOtoUserModelMapper,
                          UserInfoDTOtoUserModelMapper userInfoDTOtoUserModelMapper){
        this.userService = userService;
        this.userModelToUserInfoDTOMapper = userModelToUserInfoDTOMapper;
        this.userDTOtoModelMapper = userDTOtoModelMapper;
        this.passwordDTOtoUserModelMapper = passwordDTOtoUserModelMapper;
        this.userInfoDTOtoUserModelMapper = userInfoDTOtoUserModelMapper;
    }


    @GetMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userModelToUserInfoDTOMapper.transform(userService.findUserById(id).get()));
    }

    @PostMapping("/api/user/{id}")
    ///TODO this method with update
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO){
        userService.updateUser(id, userInfoDTOtoUserModelMapper.transform(userInfoDTO));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/api/user/password/{id}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO){
        try {
            userService.changePassword(id, passwordDTO.getNewPassword(),
                    passwordDTOtoUserModelMapper.transform(passwordDTO));
        } catch (PasswordException ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
