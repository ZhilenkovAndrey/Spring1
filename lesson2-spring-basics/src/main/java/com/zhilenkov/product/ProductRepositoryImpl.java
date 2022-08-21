package com.zhilenkov.product;

import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final AtomicLong idAtomic = new AtomicLong(0);
    private final Map<Long, Product> productMap = new HashMap<>();

    @PostConstruct
    public void init() {
        insert(new Product("Milk", 30.0));
        insert(new Product("Bread", 25.0));
        insert(new Product("Cheese", 80.0));
        insert(new Product("Meat", 180.0));
        insert(new Product("Fish", 240.0));
        insert(new Product("SomeProduct", 340.0));
        insert(new Product("OtherProduct", 240.0));
        insert(new Product("Nothing", 0.0));
        insert(new Product("AnyProduct", 140.0));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList(productMap.values());
    }

    @Override
    public void insert(Product product) {
        Long id = idAtomic.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    @Override
    public Product find(Long id) {
        return productMap.get(id);
    }

    @Override
    public Product find(String title) {
        return productMap.get(title);
    }
}
