package com.happytech.ElectronicStore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User extends BaseEntityClass{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name="user_name")
    private String name;
    @Column(name="user_email",unique = true)
    private String email;

    @Column(name="user_password", length = 10)
    private String password;
    @Column(name="user_gender")
    private String gender;
    @Column(name="user_about", length = 100)
    private String about;
    @Column(name="user_image_name")
    private String imageName;
}
