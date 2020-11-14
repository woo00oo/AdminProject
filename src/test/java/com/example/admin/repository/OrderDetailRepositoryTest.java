package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import com.example.admin.model.entity.OrderDetail;
import com.example.admin.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends AdminApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        //orderDetail.setOrderAt(LocalDateTime.now());

        Optional<User> user  = userRepository.findById(1L);
        //orderDetail.setUserId(user.get().getId());

        Optional<Item> item = itemRepository.findById(1L);
        //orderDetail.setItemId(item.get().getId());

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(newOrderDetail);
    }


}
