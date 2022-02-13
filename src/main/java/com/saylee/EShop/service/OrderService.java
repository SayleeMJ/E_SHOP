package com.saylee.EShop.service;

import com.saylee.EShop.entity.MyOrder;
import com.saylee.EShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<MyOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    public MyOrder addOrderDetails(MyOrder order){
        return orderRepository.save(order);
    }
 }
