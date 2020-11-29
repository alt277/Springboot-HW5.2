package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;

public final class ProductSpecificatiom {

    public static Specification<Product> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Product> titleLike(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> priceGreaterThan(BigDecimal price) {
        return (root, query, builder) -> builder.greaterThan(root.get("price"),price);
    }
}