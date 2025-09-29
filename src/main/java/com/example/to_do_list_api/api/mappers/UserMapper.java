package com.example.to_do_list_api.api.mappers;

import com.example.to_do_list_api.api.dto.UserRequestDto;
import com.example.to_do_list_api.api.dto.UserResponseDto;
import com.example.to_do_list_api.persistence.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    @Mapping(target = "role", constant = "USER")
    User toEntity(UserRequestDto userRequestDto);

}
