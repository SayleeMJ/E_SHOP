package com.saylee.EShop.dto;

import com.saylee.EShop.entity.Category;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Integer categoryId;
    public Double price;
    private String size;
    private String color;
    private String Description;
    private  String imageName;
}
