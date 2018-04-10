package com.murgaloids.server.review;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called reviewRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {
    boolean existsById(Long Id);
    boolean existsByRevieweeId(Long Id);
    Review findById(Long id);
    List<Review> findByRevieweeId(Long id);
}