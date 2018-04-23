package com.murgaloids.server.message;

import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called studentRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
    boolean existsByConversationId(String id);
    Message findByConversationId(String id);
}