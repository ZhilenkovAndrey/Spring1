package com.zhilenkov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {
    private final AtomicLong idAtomic = new AtomicLong();
    private final Map<Long, Product> productMap = new HashMap<>();

    protected void insert(Product product) {
        long id = idAtomic.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    protected List<Product> findAll() {
        return new ArrayList(productMap.values());
    }
}
