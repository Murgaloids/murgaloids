package com.murgaloids.server.item;

import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called itemPhotoRepository.
 *
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ItemPhotoRepository extends CrudRepository<ItemPhoto, Long> {
    boolean existsById(Long Id);
    ItemPhoto findById(Long id);
    boolean existsByItemId(Long itemId);
    ItemPhoto findByItemId(Long itemId);
}