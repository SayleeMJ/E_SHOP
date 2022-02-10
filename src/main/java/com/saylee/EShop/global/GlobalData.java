package com.saylee.EShop.global;

import com.saylee.EShop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }


}
