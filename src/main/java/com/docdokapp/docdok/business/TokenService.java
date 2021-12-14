package com.docdokapp.docdok.business;

import com.docdokapp.docdok.domain.AccountCredentials;

public interface TokenService {
    AccountCredentials decodeToken(String token);

    String decodePassword(String password);

    String encodeEmail(String email);
}
