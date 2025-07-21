package com.data.session_07.model.service;

import com.data.session_07.model.entity.Product;
import com.data.session_07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 1 bean thuộc service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public boolean deleteById(Long id) {
        return productRepository.delete(id);
    }

    @Transactional
    public boolean checkProductNameExists(String productName) {
        return productRepository.checkProductNameExists(productName);
    }

    @Transactional(readOnly = true)
    public List<Product> searchAndFilterProducts(Long categoryId, String searchTerm, String sortOrder) {
        return productRepository.searchAndFilter(categoryId, searchTerm, sortOrder);
    }

}
