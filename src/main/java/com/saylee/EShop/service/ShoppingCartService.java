package com.saylee.EShop.service;

import com.saylee.EShop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository cartRepository;
}
