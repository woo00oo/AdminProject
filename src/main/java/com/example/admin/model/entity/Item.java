package com.example.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    // 1 : n

    // LAZY = 지연 로딩(조인이 필요한 메서드를 호출하는 경우 조인 시작) 1:n, n:1, n:m
    // EAGER = 즉시 로딩(연관 관계가 설정된 모든 속성에 대한 조인이 일어남) 1:1

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

}
