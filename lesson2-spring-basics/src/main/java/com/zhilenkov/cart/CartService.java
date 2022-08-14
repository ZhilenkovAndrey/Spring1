package com.zhilenkov.cart;

import com.zhilenkov.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class CartService {
    private ProductService productService;
    private Cart cart;

    @Autowired
    public CartService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCurrentCart() {
        return this.cart;
    }

    public void addToCart(long id) {
        cart.add(productService.find(id));
    }

    public void addToCart(String title) {
        cart.add(productService.find(title));
    }

    public void clearCurrentCart() {
        cart.clear();
    }

    public void removeFromCart(String title) {
        cart.remove(productService.find(title));
    }

    public void removeFromCart(long id) {
        cart.remove(productService.find(id));
    }
}

