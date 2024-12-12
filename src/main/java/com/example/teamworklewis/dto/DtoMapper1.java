package com.example.teamworklewis.dto;

import com.example.teamworklewis.model.User;

import java.util.function.Function;

public class DtoMapper1 implements Function<User,UserDto> {




    @Override
    public UserDto apply(User user) {

        return new UserDto(user.getId(),user.getUsername(), user.getEmail());

    }
}
