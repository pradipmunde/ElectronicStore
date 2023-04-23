package com.happytech.ElectronicStore.Services.Impl;

import com.happytech.ElectronicStore.dtos.UserDto;
import com.happytech.ElectronicStore.exception.ResourceNotFoundException;
import com.happytech.ElectronicStore.helper.PageableResponse;
import com.happytech.ElectronicStore.model.User;
import com.happytech.ElectronicStore.repository.UserRepo;
import com.happytech.ElectronicStore.services.Impl.UserServiceImpl;
import com.happytech.ElectronicStore.services.UserServiceI;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.IMessageContext;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest  {
    @MockBean
    UserRepo userRepo;
    @Autowired
    UserServiceI userServiceI;
    @Autowired
    ModelMapper modelMapper;

    User user;
    UserDto userDto;
    User user1;
    List<User> users;


    @BeforeEach
    public void init() {
        user = User.builder().name("pradip")
                .email("pradipmunde92@gmail.com")
                .about("this is testing method")
                .gender("male")
                .imageName("abc.jpg")
                .password("abc")
                .build();
        userDto = UserDto.builder()
                .name("pradip munde")
                .email("pradipmunde92@gmail.com")
                .about("this is updated method")
                .gender("male")
                .imageName("xyz.png")
                .password("abcd")
                .build();

        user1 = User.builder().name("prathmesh")
                .email("prathmesh@gmail.com")
                .about("I am Tester ")
                .gender("male")
                .password("prathm")
                .imageName("pqr.png").build();

        users = new ArrayList<>();
        users.add(user);
        users.add(user1);
    }


    @Test
    void createUserTest() {
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        UserDto user1 = userServiceI.createUser(modelMapper.map(user, UserDto.class));
        System.out.println(user1.getName());
        Assertions.assertNotNull(user1);
        Assertions.assertEquals("pradip", user1.getName());
    }

    @Test
    void updateUserTest() {
        Long userId = 10L;
        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        //update
       UserDto updatedUser = userServiceI.updateUser(userDto, userId);
       // UserDto updatedUser=modelMapper.map(user,UserDto.class);
        System.out.println(updatedUser.getName());
        System.out.println(updatedUser.getImageName());
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(userDto.getName(),updatedUser.getName(), "name is not validated");
        Assertions.assertEquals("pradip munde", updatedUser.getName());

        //multiple assertions are we can perform
        // Assertions.assertThrows(ResourceNotFoundException.class,()->userServiceI.updateUser(userDto,19L));
    }

    @Test
    void deleteUser() {
        Long userId=10l;

        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        userServiceI.deleteUser(userId);

        Mockito.verify(userRepo, Mockito.times(1)).delete(user);
    }

    @Test
    void getAllUser() {
       // List<User> users= Arrays.asList(user,user1);
        Page<User> page=new PageImpl<>(users);
        Mockito.when(userRepo.findAll((Pageable)Mockito.any())).thenReturn(page);
        PageableResponse<UserDto> allUsers = userServiceI.getAllUser(1, 2, "name", "asc");
        Assertions.assertEquals(2,allUsers.getContent().size());

    }

    @Test
    void getSingleUser() {
        Long id=12l;
        Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(user));
        UserDto singleUser = userServiceI.getSingleUser(id);
        Assertions.assertThrows(ResourceNotFoundException.class, ()->userServiceI.getSingleUser(13l));

    }

    @Test
    void getSingleUserByEmail() {
        String emailId="pradipmunde92";
        Mockito.when(userRepo.findByEmail(emailId)).thenReturn(Optional.of(user));
        UserDto userDto1 = userServiceI.getSingleUserByEmail(emailId);
        Assertions.assertThrows(ResourceNotFoundException.class,()->userServiceI.getSingleUserByEmail("pradip"));

    }

    @Test
    void searchUser() {
        String keyword="pradip";
        Mockito.when(userRepo.findByNameContaining(keyword)).thenReturn(users);

        List<UserDto> userDtos = userServiceI.searchUser(keyword);
        Assertions.assertEquals(2,userDtos.size());
    }
}