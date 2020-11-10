package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("TestUser04");
        user.setEmail("Test@test.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("testUser4");

        User newUser = userRepository.save(user);
        System.out.print("newUser:"+newUser);
    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            System.out.println("user:"+selectUser);
            System.out.println("email : "+selectUser.getEmail());
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("ppppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);

        });
    }

    public void delete(){

    }


}
