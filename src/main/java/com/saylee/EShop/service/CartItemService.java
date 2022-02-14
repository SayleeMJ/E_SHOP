package com.saylee.EShop.service;

import com.saylee.EShop.entity.CartItem;
import com.saylee.EShop.entity.User;
import com.saylee.EShop.repository.CartItemRepository;
import com.saylee.EShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> listCartItems(Integer id){
        return  cartItemRepository.findByUserId(id);
    }

    public CartItem addItemsToCart(CartItem cartItem){
        return  cartItemRepository.save(cartItem);
    }
    public void  removeById(Integer id){
        cartItemRepository.deleteById(id);
    }
}
