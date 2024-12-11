package com.example.teamworklewis.dto;

import com.example.teamworklewis.model.User;

import java.util.function.Function;

public class TheBetterDtoMapper implements Function<User,UserDto> {



    //Polymorphisms enabled
    @Override
    public UserDto apply(User user) {

        return new UserDto(user.getId(),user.getUsername(), user.getEmail());

    }
}
