package com.altera1.springmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altera1.springmongo.model.Product;
import com.altera1.springmongo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new Error("product with id" + id + "is not found");
        });

    }

    @Override
    public Product update(String id, Product product) {
        Product productById = this.findById(id);
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        productById.setQty(product.getQty());
        return productRepository.save(productById);
    }

    @Override
    public void delete(String id) {
        Product productById = this.findById(id);
        productRepository.delete(productById);
    }

}
