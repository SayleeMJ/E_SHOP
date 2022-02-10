package com.saylee.EShop.service;

import com.saylee.EShop.entity.Product;
import com.saylee.EShop.entity.User;
import com.saylee.EShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeUserById(Integer id){
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }
}
