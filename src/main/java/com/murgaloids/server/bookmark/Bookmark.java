package com.murgaloids.server.bookmark;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Bookmark is a class representation of a bookmark.
 */
@Entity
@Table(name="bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", columnDefinition="int(11)")
    private Long id;

    @Column(name="buyer_id", columnDefinition="int(11)")
    private Long buyerId;

    @Column(name="item_id", columnDefinition="int(11)")
    private Long itemId;

    protected Bookmark() {}
    public Bookmark(Long id, Long buyerId, Long itemId) {
        this.id = id;
        this.buyerId = buyerId;
        this.itemId = itemId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return this.buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}