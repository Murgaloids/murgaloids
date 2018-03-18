package com.murgaloids.server.itemCondition;

import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called studentRepository.
 *
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ItemConditionRepository extends CrudRepository<ItemCondition, Long> {
    boolean existsById(Long id);
}