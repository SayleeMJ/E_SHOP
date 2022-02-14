package com.saylee.EShop.controller;

import com.saylee.EShop.entity.CartItem;
import com.saylee.EShop.entity.User;
import com.saylee.EShop.repository.UserRepository;
import com.saylee.EShop.service.CartItemService;
import com.saylee.EShop.service.CategoryService;
import com.saylee.EShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemService cartItemService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable Integer id, Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
    }
}
