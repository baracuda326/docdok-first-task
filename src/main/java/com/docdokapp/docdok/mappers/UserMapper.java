package com.docdokapp.docdok.mappers;

import com.docdokapp.docdok.domain.UserEntity;
import com.docdokapp.docdok.domain.dto.UserModel;
import com.docdokapp.docdok.domain.dto.UserResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    UserResponseModel userToUserResponseModel(UserEntity userEntity);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName", defaultValue = "First name not defined")
    @Mapping(source = "lastName", target = "lastName", defaultValue = "Last name not defined")
    @Mapping(target = "date", expression = "java(LocalDateTime.now().toString())")
    UserEntity userRegistrationModelToUserEntity(UserModel user);
}
