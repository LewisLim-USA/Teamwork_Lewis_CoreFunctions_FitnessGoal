package com.example.teamworklewis.controller.service;

import com.example.teamworklewis.dao.DAOImpObj;
import com.example.teamworklewis.dao.DAOInterface;
import com.example.teamworklewis.dto.DtoMapper;
import com.example.teamworklewis.dto.DtoMapper1;
import com.example.teamworklewis.dto.UserDto;
import com.example.teamworklewis.model.User;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {

    private static ArrayList<User> userContainer = new ArrayList<User>();
    private DAOInterface<User> userprofile = new DAOImpObj<User>();

    private static UserController single_instance = null;

    public static ArrayList<User> getUserContainer() {
        return userContainer;
    }

    public void getAllUser() {
        userContainer.clear();
        for (User p : userprofile.getAll()) {

            userContainer.add((User) p);
            //System.out.println(p);
        }

    }

    public List<UserDto> getUser() {
        return userContainer.stream()
                .map(new DtoMapper1()).toList();
    }


    public UserDto getUserById(int d) {
        UserDto f = null;
        User s = userprofile.getObj(d);
        if (s != null)
            f = DtoMapper.map(userprofile.getObj(d));
        //System.out.println(f);
        return f;


    }

    public String addUser(Object[] s) {
        //System.out.println(userContainer.toString());


        boolean status = userContainer.stream().filter(p -> p.getId() == (Integer) s[4]).findAny().isPresent();

        if (status) {
            return "Add fail - id exists";
        }else {
            userContainer.add(new User((String) s[0], (String) s[1], (Integer) s[2], (String) s[3], (Integer) s[4]));
            String m = userprofile.add(userContainer);
            return m;
        }


        //System.out.println(userContainer.toString());

    }

    private UserController() {

    }

    public static synchronized UserController getInstance() {
        if (single_instance == null) {
            single_instance = new UserController();
            single_instance.getAllUser();
        }
        return single_instance;
    }


}
