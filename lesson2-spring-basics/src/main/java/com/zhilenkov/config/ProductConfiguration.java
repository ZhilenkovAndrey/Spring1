package com.zhilenkov.config;

import com.zhilenkov.cart.Cart;
import com.zhilenkov.cart.CartService;
import com.zhilenkov.order.OrderService;
import com.zhilenkov.product.ProductService;
import com.zhilenkov.product.ProductRepository;
import com.zhilenkov.product.ProductRepositoryImpl;
import com.zhilenkov.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class ProductConfiguration {
//
//    @Bean
//    protected ProductRepositoryImpl productRepository() {
//        return new ProductRepositoryImpl();
//    }
//
//    @Bean
//    protected ProductService productService(ProductRepository productRepository) {
//        return new ProductService(productRepository);
//    }
//
//    @Bean
//    @Scope("prototype")
//    protected Cart cart() {
//        return new Cart();
//    }

    @Bean
    protected OrderService orderService() {
        return new OrderService(new UserService(),
                                new CartService(new ProductService(new ProductRepositoryImpl())),
                                new ProductService(new ProductRepositoryImpl()));
    }
}
