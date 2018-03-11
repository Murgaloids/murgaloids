package com.murgaloids.server.bookmark;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called bookmarkRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
    boolean existsById(Long Id);
    Bookmark findById(Long id);
    List<Bookmark> findByBuyerId(Long id);
}