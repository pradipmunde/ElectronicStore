package com.happytech.ElectronicStore.services;

import com.happytech.ElectronicStore.dtos.UserDto;

import java.util.List;

public interface UserServiceI {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, Long userId);

    //delete
    void deleteUser(Long userId);

    //get all users
    List<UserDto> getAllUser();

    //get single user
    UserDto getSingleUser(Long userId);

    //get single user by email
    UserDto getSingleUserByEmail(String email);

    //search user
    List<UserDto> searchUser(String keyword);

    //other  user specific features
}
