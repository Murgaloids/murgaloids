package com.murgaloids.server.review;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Review is a class representation of a revview.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @NonNull
    @Column(name = "item_id", columnDefinition = "int(11)")
    private Long itemId;

    @NonNull
    @Column(name = "reviewee_id", columnDefinition = "int(11)")
    private Long revieweeId;

    @NonNull
    @Column(name = "reviewer_id", columnDefinition = "int(11)")
    private Long reviewerId;

    @Column(name = "rating", columnDefinition = "int(11)")
    private Long rating;

    @NonNull
    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;
}