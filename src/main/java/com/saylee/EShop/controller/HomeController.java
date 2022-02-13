package com.saylee.EShop.controller;

import com.saylee.EShop.service.CategoryService;
import com.saylee.EShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
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

    @RequestMapping("/contact")
    public String showContact(){
        return "contact";
    }

    @RequestMapping("/blog")
    public String showBlog(){
        return "blog";
    }
}
