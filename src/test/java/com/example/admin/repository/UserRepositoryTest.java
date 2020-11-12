package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void create(){
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("Test@test.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("testUser5");

        User newUser = userRepository.save(user);
        System.out.print("newUser:"+newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findByAccount("TestUser01");

        user.ifPresent(selectUser ->{
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                System.out.println(detail.getItem());
            });
        });
    }

    @Test
    //@Transactional
    public void update(){
        Optional<User> user = userRepository.findById(3L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("ppppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);

        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent()); //true

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assert.assertFalse(deleteUser.isPresent()); //false

    }


}
