package com.zhilenkov.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private Double cost;

    public Product(Long id, String title, Double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, Double cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\n" + "Product: " + id + ". " + title +
                " cost = " + cost;
    }
}
