package com.zhilenkov.cart;

import com.zhilenkov.product.Product;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    public void remove(Product product) {
        products.remove(product);
    }
}

