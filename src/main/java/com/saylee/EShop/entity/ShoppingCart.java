package com.saylee.EShop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Transient
    private  Double totalPrice;

    @Transient
    private Integer itemNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<CartItem> items;

    @OneToMany(cascade = CascadeType.ALL)
    private  Collection<User> users;

    public Double getTotalPrice() {
        Double sum = 0.0;
        for(CartItem item : this.items){
            sum = sum + item.getProduct().getPrice();
        }
        return sum;
    }

    public Integer getItemNumber() {
        return this.items.size();
    }
}
