package com.happytech.ElectronicStore.repository;

import com.happytech.ElectronicStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
   Optional<User> findByEmail(String email);

   Optional<User> findByEmailAndPassword(String email, String password);

   List<User> findByNameContaining(String keywords);

}
