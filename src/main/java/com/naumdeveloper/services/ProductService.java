package com.naumdeveloper.services;

import com.naumdeveloper.entities.Product;
import com.naumdeveloper.repositories.ProductRepository;
import com.naumdeveloper.repositories.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partName, Integer page) {
        Specification<Product> spec = Specification.where(null);
        // select s from Product s where true
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.scoreGreaterOrEqualsThan(minPrice));
            //select s from Product s where true AND s.price > minPrice
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.scoreLessThanOrEqualsThan(maxPrice));
            // select s from Product s where true AND s.price > minPrice AND s.price < maxPrice
        }
        if (partName != null) {
            spec = spec.and(ProductSpecifications.nameLike(partName));
            // select s from Product s where true AND s.price > minPrice AND s.name LIKE %partName%
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 50));
    }

    public Optional<Product> finfById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product productServiceSave(Product product) {
        return productRepository.save(product);
    }


}
