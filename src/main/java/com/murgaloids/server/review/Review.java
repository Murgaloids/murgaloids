package com.murgaloids.server.review;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Review is a class representation of a revview.
 */
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "item_id", columnDefinition = "int(11)")
    private Long itemId;

    @Column(name = "reviewee_id", columnDefinition = "int(11)")
    private Long revieweeId;

    @Column(name = "reviewer_id", columnDefinition = "int(11)")
    private Long reviewerId;

    @Column(name = "rating", columnDefinition = "int(11)")
    private Long rating;

    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;

    protected Review() {}
    public Review(
        Long id,
        Long itemId,
        Long revieweeId,
        Long reviewerId,
        Long rating,
        String description
    ) {
        this.id =id;
        this.itemId = itemId;
        this.revieweeId = revieweeId;
        this.reviewerId = reviewerId;
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getRevieweeId() {
        return this.revieweeId;
    }

    public void setRevieweeId(Long revieweeId) {
        this.revieweeId = revieweeId;
    }

    public Long getReviewerId() {
        return this.reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getRating() {
        return this.rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}