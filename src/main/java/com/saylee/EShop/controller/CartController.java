package com.saylee.EShop.controller;

import com.saylee.EShop.entity.CartItem;
import com.saylee.EShop.entity.MyOrder;
import com.saylee.EShop.entity.Product;
import com.saylee.EShop.entity.User;
import com.saylee.EShop.repository.UserRepository;
import com.saylee.EShop.service.CartItemService;
import com.saylee.EShop.service.OrderService;
import com.saylee.EShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, Principal principal){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if (user.isPresent()){
            List<CartItem> cart = cartItemService.listCartItems(user.get().getId());
            Double sum = 0.0;
            for (int i =0 ;i<cart.size();i++){
                sum = sum + (cart.get(i).getProduct().getPrice() * cart.get(i).getQuantity());
            }
            model.addAttribute("total", sum);
            model.addAttribute("cart", cart);
            return "cart";
        }else {
            return "shop";
        }
    }


    @RequestMapping("/addToCart/{id}")
    public  String addToCart(Principal principal ,@PathVariable Long id, HttpServletRequest request, Model model){;
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if(user.isPresent()){
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            Product product =  productService.getProductById(id).get();
            Integer addedQuantity = quantity;
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user.get());
            cartItem.setQuantity(quantity);
            cartItemService.addItemsToCart(cartItem);
            model.addAttribute("message", "Item Added successfully");
            return "redirect:/shop";
        }else{
            return "redirect:/shop";
        }
    }
    @RequestMapping("/orderPlaced")
    public String checkoutProduct(Model model, Principal principal, HttpServletRequest request){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if(user.isPresent()){
            List<CartItem> cartItem = cartItemService.listCartItems(user.get().getId());
            for(int i = 0; i< cartItem.size();i++){
                MyOrder myOrder = new MyOrder();
                myOrder.setProduct(cartItem.get(i).product);
                myOrder.setUser(user.get());
                myOrder.setDate(new Date());
                myOrder.setTime(new Date());
                myOrder.setQuantity(cartItem.get(i).getQuantity());
                orderService.addOrderDetails(myOrder);
                cartItemService.removeById(cartItem.get(i).getId());
            }
            return "redirect:/shop";
        }
        System.out.println("Invalid User");

        return "redirect:/shop";
    }

    @GetMapping("/deleteCart")
    public String getCartRemove(Principal principal, Model model){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if(user.isPresent()){
            List<CartItem> cartItem = cartItemService.listCartItems(user.get().getId());
            for(int i=0; i<cartItem.size();i++){
                cartItemService.removeById(cartItem.get(i).getId());
            }
            model.addAttribute("message","Products removed successfully!");
        }
        return "redirect:/cart";
    }

    @RequestMapping("/updateCart")
    public  String updateCart(Principal principal, HttpServletRequest request, Model model){;
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        List<CartItem> cartItem = cartItemService.listCartItems(user.get().getId());
        if(user.isPresent()){
            for(int i=0; i<cartItem.size();i++){
                CartItem cartItem1 = cartItem.get(i);
                Integer quantity = Integer.parseInt(request.getParameter(cartItem.get(i).getId().toString()));
                if(quantity <= 0){
                    cartItemService.removeById(cartItem.get(i).getId());
                }
                else{
                    cartItem1.setQuantity(quantity);
                    cartItemService.addItemsToCart(cartItem1);
                }
            }
            return "redirect:/cart";
        }else{
            return "redirect:/shop";
        }
    }

    @RequestMapping("/orderHistory")
    public String showOrderHistory(Principal principal, Model model){
        Optional<User> user = userRepository.findUserByEmail(principal.getName());
        if (user.isPresent()){
            List<MyOrder> myOrders = orderService.listOrderHistory(user.get().getId());
            model.addAttribute("order", myOrders);
            return "myOrder";
        }else {
            return "redirect:/shop";
        }
    }
}
