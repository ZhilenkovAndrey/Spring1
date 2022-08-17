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
        productRepository.init();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product find(Long id) {
        return productRepository.find(id);
    }

    public Product find(String title) {
        return productRepository.find(title);
    }
}