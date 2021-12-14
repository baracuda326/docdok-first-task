package com.docdokapp.docdok.business;

import com.docdokapp.docdok.domain.dto.UserModel;
import com.docdokapp.docdok.domain.dto.UserResponseModel;

public interface UserService {
    UserResponseModel registrationUser(UserModel userModel);

    UserResponseModel saveUserPassword(String token);
}
