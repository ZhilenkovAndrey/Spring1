package com.zhilenkov.product;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final AtomicLong idAtomic = new AtomicLong();
    private final Map<Long, Product> productMap = new HashMap<>();

    @Override
    public List<Product> findAll() {
        return new ArrayList(productMap.values());
    }

    @Override
    public void insert(Product product) {
        long id = idAtomic.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    @Override
    public void delete(long id) {
        productMap.remove(id);
    }

    @Override
    public void delete(String title) {
        productMap.remove(title);
    }

    @Override
    public Product find(long id) {
        return productMap.get(id);
    }

    @Override
    public Product find(String title) {
        return productMap.get(title);
    }
}
