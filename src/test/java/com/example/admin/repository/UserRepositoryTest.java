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
    public void create(){
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAT = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);


        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){

        Optional<User> user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        user.ifPresent(selectUser ->{
                user.get().getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("--------------주문 묶음--------------");
                System.out.println("수령인 : " +orderGroup.getRevName());
                System.out.println("수령 : " +orderGroup.getRevAddress());
                System.out.println("총금액 : " +orderGroup.getTotalPrice());
                System.out.println("총수량 : " +orderGroup.getTotalQuantity());

                System.out.println("--------------주문 상세--------------");
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 :" +orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 :" +orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 :"+orderDetail.getItem().getName());
                    System.out.println("고객센터 번호:"+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 :"+orderDetail.getStatus());
                    System.out.println("도착예정 일자 :"+orderDetail.getArrivalDate());
                });
            });
        });

        Assert.assertNotNull(user);

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
