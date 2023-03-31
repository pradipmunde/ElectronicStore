package com.happytech.ElectronicStore.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {



    @Size(min=4,max=20,message="username must be min 4 characters....!!")
    private String name;
    @Pattern(regexp="^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message="Invalid EmailId....!!")
    @NotBlank(message="Email is required...!!")
    private String email;

    @NotBlank(message="Password is required.....!!")
    private String password;

    @Size(min=4,max=6,message="Invalid gender....!!")
    private String gender;

    @NotBlank(message="Write something about yourself....!!")
    private String about;

    private String imageName;
}
