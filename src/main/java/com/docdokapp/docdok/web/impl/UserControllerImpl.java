package com.docdokapp.docdok.web.impl;

import com.docdokapp.docdok.business.UserService;
import com.docdokapp.docdok.domain.dto.UserModel;
import com.docdokapp.docdok.domain.dto.UserResponseModel;
import com.docdokapp.docdok.web.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponseModel> registrationUser(UserModel userModel) {
        return new ResponseEntity<>(userService.registrationUser(userModel), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseModel> saveUserPassword(String token) {
        return new ResponseEntity<>(userService.saveUserPassword(token), HttpStatus.OK);
    }
}
