package com.saylee.EShop.repository;

import com.saylee.EShop.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<MyOrder,Long> {
    List<MyOrder> findByUserId(Integer id);
}
