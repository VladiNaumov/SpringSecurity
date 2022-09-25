package com.naumdeveloper.repositories.specifications;


import com.naumdeveloper.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> scoreGreaterOrEqualsThan(Integer score) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), score);

    }

    public static Specification<Product> scoreLessThanOrEqualsThan(Integer score) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), score);
    }

    public static Specification<Product> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
