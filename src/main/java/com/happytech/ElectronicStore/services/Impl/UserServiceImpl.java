package com.happytech.ElectronicStore.services.Impl;

import com.happytech.ElectronicStore.dtos.UserDto;
import com.happytech.ElectronicStore.model.User;
import com.happytech.ElectronicStore.repository.UserRepo;
import com.happytech.ElectronicStore.services.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Slf4j
@Service
public class UserServiceImpl implements UserServiceI {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

     logger.info("Initiating Dao call for save user");

        //generate unique id in string format
       //String userId= UUID.randomUUID().toString();
        //userDto.setUserId(userId);

        //convert dto -> entity
        User user= dtoToEntity(userDto);
        User savedUser= userRepo.save(user);

        logger.info("completed the Dao call for save user");

       //convert entity->dto
        UserDto newDto=entityToDto(savedUser);
        return newDto;
    }

    //creating method for above dto to entity move cursor on it and press Alt+Enter//
    private User dtoToEntity(UserDto userDto) {
//        User user=User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getPassword())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
//        return user;

        // Instead of doing above manual conversion of userDto to user class we have to use simple modelMapper for mapping userDto to user class
        return modelMapper.map(userDto, User.class);


    }
    private UserDto entityToDto(User savedUser) {
        UserDto userDto = modelMapper.map(savedUser, UserDto.class);

//        UserDto userDto=UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .imageName(savedUser.getImageName())
//                .build();
//        return userDto;
        // Instead of doing above manual conversion of user to useDto class we have to use simple modelMapper for mapping user to userDto class
                 // return modelMapper.map(savedUser,UserDto.class);
        return userDto;

    }


    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        logger.info("Initiating the dao call for update user");

        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found for given userId"));
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updatedUser=userRepo.save(user);
        UserDto updatedDto=entityToDto(updatedUser);

        logger.info("completed the dao call for update user");

        return updatedDto;
    }

    @Override
    public void deleteUser(Long userId) {

        logger.info("Initiating the dao call for delete user");

        User user=this.userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not found for given id"));
       this.userRepo.delete(user);

       logger.info("completed dao call for delete user");
    }

    @Override
    public List<UserDto> getAllUser() {

        logger.info("Initiating the dao call for get all users");

        List<User> users = userRepo.findAll();

        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

       logger.info("completed the dao call for save all users");
        return dtoList;

    }

    @Override
    public UserDto getSingleUser(Long userId) {
        logger.info("Initiating dao call for getting single user using user id");

        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found for given id"));
        //UserDto dto = this.modelMapper.map(user, UserDto.class);

        logger.info("completed the dao call for getting single user using user id ");

        return entityToDto(user);
    }

    @Override
    public UserDto getSingleUserByEmail(String email) {

        logger.info("Initiating dao call for getting single user by using email");

        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found for given email id and password"));

        logger.info("completed the dao  call for getting single user by using email");

        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        logger.info("Initiating dao call for searching user based on keyword");

        List<User> users = userRepo.findByNameContaining(keyword);
        List<UserDto> dtoList2 = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        logger.info("completed the dao call for searching user based on keyword");
        return dtoList2;

    }
}
