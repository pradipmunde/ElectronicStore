package com.happytech.ElectronicStore.controller;

import com.happytech.ElectronicStore.constants.AppConstants;
import com.happytech.ElectronicStore.dtos.UserDto;
import com.happytech.ElectronicStore.helper.ApiResponse;
import com.happytech.ElectronicStore.services.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceI userServiceI;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto){

    logger.info("Initiating the request for save user");
    UserDto user = this.userServiceI.createUser(userDto);

    logger.info("completed the request for save user");
    return new ResponseEntity<>(AppConstants.USER_SAVE, HttpStatus.CREATED);

    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId){

        logger.info("Initiating the request for updating user");

        UserDto userDto1 = this.userServiceI.updateUser(userDto, userId);

        logger.info("completed the request for updating user");
        return new ResponseEntity<>(userDto1,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){

        logger.info("Initiating the request for delete user");
        this.userServiceI.deleteUser(userId);
        ApiResponse message =ApiResponse
                .builder()
                .message("user is deleted successfully....!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        logger.info("completed the request for deleting user");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("allUser")
    public ResponseEntity<List<UserDto>> allUsers(){

        logger.info("Initiating the request for get all users");
        List<UserDto> allUser = this.userServiceI.getAllUser();

        logger.info("completed the request for get all user");
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @GetMapping("/userById/{userId}")
    public ResponseEntity<UserDto> userById(@PathVariable Long userId){

        logger.info("Initiating the request for getting single  user using userId");

        // UserDto singleUser = this.userServiceI.getSingleUser(userId);
        // return new ResponseEntity<>(singleUser, HttpStatus.ACCEPTED);

        logger.info("completed the request for getting  user using userId");
        return new ResponseEntity<>(userServiceI.getSingleUser(userId), HttpStatus.OK);
    }
    @GetMapping("/userByEmail/{emailId}")
    public ResponseEntity<UserDto> userByEmail(@PathVariable String email){

        logger.info("Initiating the request for getting user using email");
        UserDto user = this.userServiceI.getSingleUserByEmail(email);

        logger.info("completed the request for getting user using email");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword){

        logger.info("Initiating the request for getting user using keyword");
        List<UserDto> userDtos = this.userServiceI.searchUser(keyword);

        logger.info("completed the request for searching user using keyword");
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }


}
