package com.saylee.EShop.controller;

import com.saylee.EShop.entity.CartItem;
import com.saylee.EShop.entity.Product;
import com.saylee.EShop.entity.User;
import com.saylee.EShop.global.GlobalData;
import com.saylee.EShop.repository.UserRepository;
import com.saylee.EShop.service.CartItemService;
import com.saylee.EShop.service.ProductService;
import com.saylee.EShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, Principal principal){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if (user.isPresent()){
            List<CartItem> cart = cartItemService.listCartItems(user.get().getId());
            model.addAttribute("cart", cart);
            return "cart";
        }else {
            return "shop";
        }
    }


    @GetMapping("/addToCart/{id}")
    public  String addToCart(Principal principal ,@PathVariable Long id, HttpServletRequest request){
        Integer quantity = 1;
        Optional<Product> product =  productService.getProductById(id);
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if (user.isPresent()){
            Integer useId = user.get().getId();
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
//        cartItem.setProduct(product);
//        cartItem.setUser(userId);
//        cartItemService.addItemsToCart();
        return "redirect:/shop";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String getCartRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutProduct(Model model){
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
    @RequestMapping("/checkoutSuccessful")
    public String checkoutProductSuccessful(){
        return "payNow";
    }
}
