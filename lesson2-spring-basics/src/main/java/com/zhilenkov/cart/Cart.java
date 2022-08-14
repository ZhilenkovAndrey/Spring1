package com.zhilenkov.cart;

import com.zhilenkov.product.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        products.add(product);
    }

//    public void remove(String title) {
//        products.removeIf(p -> p.getTitle().equals(title));
//    }
//
//    public void remove(long id) {
//        products.removeIf(p -> p.getId().equals(id));
//    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void clear() {
        products.clear();
    }
}

