package com.example.to_do_list_api.api.mappers;

import com.example.to_do_list_api.api.dto.UserRequestDto;
import com.example.to_do_list_api.api.dto.UserResponseDto;
import com.example.to_do_list_api.persistence.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto userRequestDto);

}
