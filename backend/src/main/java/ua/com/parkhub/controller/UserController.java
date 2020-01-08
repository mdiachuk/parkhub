package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.dto.UserInfoDTO;
import ua.com.parkhub.mappers.DtoToModel.UserDtoToUserModelMapper;
import ua.com.parkhub.mappers.ModelToDto.UserModelToUserDtoMapper;
import ua.com.parkhub.service.impl.UserService;


@RestController
public class UserController {

    UserService userService;
    UserModelToUserDtoMapper userModelToDTOMapper;
    UserDtoToUserModelMapper userDTOtoModelMapper;

    @Autowired
    public UserController(UserService userService, UserModelToUserDtoMapper userModelToDTOMapper, UserDtoToUserModelMapper userDTOtoModelMapper){
        this.userService = userService;
        this.userModelToDTOMapper = userModelToDTOMapper;
        this.userDTOtoModelMapper = userDTOtoModelMapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userModelToDTOMapper.transform(userService.findUserById(id).get()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @PostMapping("/api/user")
    ///TODO this method with update
    public ResponseEntity updateUser( @RequestBody UserInfoDTO userDTO){
        userService.updateUser(userDTO);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @PostMapping("/api/user/password")
    public ResponseEntity updateUserPassword(@RequestBody PasswordDTO passwordDTO){
        //passwordDTO.setId(id);
        userService.changePassword(passwordDTO);
        return ResponseEntity.ok().build();
    }
}

