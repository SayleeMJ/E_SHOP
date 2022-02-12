package com.saylee.EShop.service;

import com.saylee.EShop.entity.Role;
import com.saylee.EShop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    private Role findRoleById(Integer id){
        return roleRepository.findById(id).get();
    }
}

