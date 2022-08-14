package com.zhilenkov.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        this.productRepository.insert(new Product("Milk", 30.0));
        this.productRepository.insert(new Product("Bread", 25.0));
        this.productRepository.insert(new Product("Cheese", 80.0));
        this.productRepository.insert(new Product("Meat", 180.0));
        this.productRepository.insert(new Product("Fish", 240.0));
        this.productRepository.insert(new Product("SomeProduct", 340.0));
        this.productRepository.insert(new Product("OtherProduct", 240.0));
        this.productRepository.insert(new Product("Nothing", 0.0));
        this.productRepository.insert(new Product("AnyProduct", 140.0));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product find(long id) {
        return productRepository.find(id);
    }

    public Product find(String title) {
        return productRepository.find(title);
    }

    public void delete(long id) {
        productRepository.delete(id);
    }

    public void delete(String title) {
        productRepository.delete(title);
    }
}