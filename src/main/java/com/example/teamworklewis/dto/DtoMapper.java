package com.example.teamworklewis.dto;

import com.example.teamworklewis.model.User;

public class DtoMapper {

public static UserDto map(User user)
{
    UserDto userDto = new UserDto(user.getId(), user.getUsername(),user.getEmail());

    return userDto;
}

}
