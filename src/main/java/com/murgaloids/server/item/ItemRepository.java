package com.murgaloids.server.item;

import org.springframework.data.repository.CrudRepository;

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
}