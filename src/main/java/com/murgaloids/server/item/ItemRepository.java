package com.murgaloids.server.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called itemRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ItemRepository extends CrudRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    boolean existsById(Long Id);
    Item findById(Long id);
    List<Item> findBySellerId(Long id);

    @Query(value = "SELECT * FROM item r WHERE r.is_item_sold = 0 ORDER BY r.date_added DESC limit ?1", nativeQuery = true)
    List<Item> findRecentItems(int numOfResult);

    @Query(value = "SELECT * FROM item WHERE LOCATE(?1, item_name) > 0", nativeQuery = true)
    List<Item> findByName(String name);

    @Query(value = "SELECT * FROM item WHERE price >= ?1", nativeQuery = true)
    List<Item> findByPriceGreaterThanOrEqual(double price);

    @Query(value = "SELECT * FROM item WHERE price <= ?1", nativeQuery = true)
    List<Item> findByPriceLessThanOrEqual(double price);

    @Query(value = "SELECT * FROM item WHERE LOCATE(?1, description) > 0", nativeQuery = true)
    List<Item> findByDescription(String description);
}