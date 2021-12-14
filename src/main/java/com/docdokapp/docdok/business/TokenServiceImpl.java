package com.docdokapp.docdok.business;

import com.docdokapp.docdok.domain.AccountCredentials;
import com.docdokapp.docdok.exceptions.TokenValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

@Slf4j
@Component
public class TokenServiceImpl implements TokenService {
    public static final String LENGTH_TOKEN_NO_VALID = "Length token no valid";

    @Override
    public AccountCredentials decodeToken(String token) {
        try {
            int index = token.indexOf(" ");
            token = token.substring(index + 1);
            getDecoder().decode(token);
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException ex) {
            log.warn("Bad token");
            throw new TokenValidationException("Bad token");
        }
        byte[] base64DecodeBytes = getDecoder().decode(token);
        token = new String(base64DecodeBytes);
        String[] auth = token.split(":");
        if (auth.length > 2) throw new TokenValidationException(LENGTH_TOKEN_NO_VALID);
        return new AccountCredentials(auth[0].toLowerCase(), auth[1]);
    }

    @Override
    public String decodePassword(String password) {
        byte[] base64DecodeBytes = getDecoder().decode(password);
        password = new String(base64DecodeBytes);
        return password;
    }

    @Override
    public String encodeEmail(String email) {
        return getEncoder().encodeToString(email.getBytes());
    }
}
