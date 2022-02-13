package com.saylee.EShop.repository;

import com.saylee.EShop.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MyOrder,Long> {
}
