package com.zhilenkov.product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product find(long id);
    Product find(String title);
    void delete(long id);
    void delete(String title);
    void insert(Product product);
}
