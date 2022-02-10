package com.saylee.EShop.repository;

import com.saylee.EShop.entity.CartItem;
import com.saylee.EShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
   List<CartItem> findByUserId(Integer id);
}
