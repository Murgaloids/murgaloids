package com.murgaloids.server.item;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called itemRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
    boolean existsById(Long Id);
    Item findById(Long id);
    List<Item> findBySellerId(Long id);

    @Query(value = "SELECT * FROM items r WHERE r.is_item_sold = 0 ORDER BY r.date_added DESC limit ?1",
    	nativeQuery = true)
    List<Item> findRecentItems(int numOfResult);
}