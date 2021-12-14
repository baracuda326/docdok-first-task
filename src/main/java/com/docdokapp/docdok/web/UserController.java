package com.docdokapp.docdok.web;

import com.docdokapp.docdok.domain.dto.UserModel;
import com.docdokapp.docdok.domain.dto.UserResponseModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/")
public interface UserController {

    @ApiOperation(value = "Registration new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="OK",response = UserResponseModel.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "User already exist")
    })
    @PostMapping(value = "/user/registration")
    ResponseEntity<UserResponseModel> registrationUser(@RequestBody UserModel userModel);

    @ApiOperation(value = "Create password for user")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="OK",response = UserResponseModel.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PostMapping(value = "/user/create/password")
    ResponseEntity<UserResponseModel> saveUserPassword(@RequestHeader("Authorization") String token);
}
