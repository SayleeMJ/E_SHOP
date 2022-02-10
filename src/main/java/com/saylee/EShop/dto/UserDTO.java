package com.saylee.EShop.dto;

import com.saylee.EShop.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String contact;
    private String address;
    private String password;
}
