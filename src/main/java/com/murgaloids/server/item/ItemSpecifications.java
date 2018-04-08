package com.murgaloids.server.item;

import org.springframework.data.jpa.domain.Specification;

public class ItemSpecifications {
    public static Specification<Item> withItemName(String name) {
        if (name == null) {
            return null;
        }
        else {
            return (root, query, cb) -> cb.greaterThan(cb.locate(root.get(Item_.itemName), name), 0);
        }
    }
    public static Specification<Item> withPriceEqualTo(Double price) {
        if (price == null) {
            return null;
        }
        else {
            return (root, query, cb) -> cb.equal(root.get(Item_.price), price);
        }
    }
    public static Specification<Item> withPriceGreaterThanOrEqualTo(Double price) {
        if (price == null) {
            return null;
        }
        else {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Item_.price), price);
        }
    }
    public static Specification<Item> withPriceLessThanOrEqualTo(Double price) {
        if (price == null) {
            return null;
        }
        else {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Item_.price), price);
        }
    }
    public static Specification<Item> withSellerId(Long sellerId) {
        if (sellerId == null) {
            return null;
        }
        else {
            return (root, query, cb) -> cb.equal(root.get(Item_.sellerId), sellerId);
        }
    }
    public static Specification<Item> withDescription(String description) {
        if (description == null) {
            return null;
        }
        else {
            return (root, query, cb) -> cb.equal(root.get(Item_.description), description);
        }
    }
}
