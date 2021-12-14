package com.docdokapp.docdok.business;

import com.docdokapp.docdok.domain.AccountCredentials;
import com.docdokapp.docdok.domain.UserEntity;
import com.docdokapp.docdok.domain.dto.UserModel;
import com.docdokapp.docdok.domain.dto.UserResponseModel;
import com.docdokapp.docdok.exceptions.NotFoundException;
import com.docdokapp.docdok.mappers.UserMapper;
import com.docdokapp.docdok.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

import static java.text.MessageFormat.format;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseModel registrationUser(UserModel userModel) {
        UserEntity userEntity = userRepository
                .save(UserMapper.INSTANCE.userRegistrationModelToUserEntity(userModel));
        log.info("Registration user with email : {}", userModel.getEmail());
        return UserMapper.INSTANCE.userToUserResponseModel(userEntity);
    }

    @Override
    public UserResponseModel saveUserPassword(String token) {
        AccountCredentials accountCredentials = tokenService.decodeToken(token);
        UserEntity user = userRepository.findUserEntityByEmail(accountCredentials.getEmail())
                .orElseThrow(() -> new NotFoundException(
                        format("User with email: {0} is not found", accountCredentials.getEmail())));
        user.setPassword(tokenService.decodePassword(accountCredentials.getPassword()));
        userRepository.save(user);
        log.info("Password was saved for user with email : {}", accountCredentials.getEmail());
        return UserMapper.INSTANCE.userToUserResponseModel(user);
    }

    @PostConstruct
    public void init() {
        UserEntity user = UserEntity.builder()
                .email("test@gmail.com")
                .firstName("Tom")
                .lastName("Backer")
                .password("pass")
                .date(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
    }
}
