package com.zhilenkov;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private Double cost;

    public Product(String title, Double cost) {
        this.title = title;
        this.cost = cost;
    }
}
