package com.murgaloids.server.conversation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called studentRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ConversationRepository extends CrudRepository<Conversation, Long>, JpaSpecificationExecutor<Conversation> {
    boolean existsById(String id);
    Conversation findById(String id);

    @Query(value="SELECT * FROM conversation WHERE (student1_id=?1 OR student2_id=?1)", nativeQuery=true)
    List<Conversation> findByUserId(String id);
}