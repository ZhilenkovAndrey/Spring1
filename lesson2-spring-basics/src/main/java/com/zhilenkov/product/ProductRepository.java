package com.zhilenkov.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();
    Product find(Long id);
    Product find(String title);
    void insert(Product product);
    void init();
}
