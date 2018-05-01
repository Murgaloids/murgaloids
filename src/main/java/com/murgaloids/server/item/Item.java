package com.murgaloids.server.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

/**
 * Item is a class representation of an item.
 */
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "seller_id", columnDefinition = "int(11)")
    private Long sellerId;

    @Column(name = "condition_type_id", columnDefinition = "int(11)")
    private Long conditionTypeId;

    @Column(name = "category_type_id", columnDefinition = "int(11)")
    private Long categoryTypeId;

    @Column(name = "item_name", columnDefinition = "varchar(254)")
    private String itemName;

    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;

    @Column(name = "price", columnDefinition = "double(10,2)")
    private Double price;

    @Column(name = "is_item_sold", columnDefinition = "tinyint(1)")
    private Boolean itemSold;

    @Column(name = "is_item_rated", columnDefinition = "tinyint(1)")
    private Boolean itemRated;

    @Column(name = "rating", columnDefinition = "int(11)")
    private Long rating;

    @Column(name = "image_source", columnDefinition = "varchar(254)")
    private String imageSource;

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "date_added", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    protected Item() {}
    public Item(
        Long id,
        Long sellerId,
        Long conditionTypeId,
        Long categoryTypeId,
        String itemName,
        String description,
        Double price,
        Boolean itemSold,
        Boolean itemRated,
        Long rating,
        String imageSource,
        Date dateAdded
    ) {
        this.id = id;
        this.sellerId = sellerId;
        this.conditionTypeId = conditionTypeId;
        this.categoryTypeId = categoryTypeId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.itemSold = itemSold;
        this.itemRated = itemRated;
        this.rating = rating;
        this.imageSource = imageSource;
        this.dateAdded = dateAdded;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getConditionTypeId() {
        return this.conditionTypeId;
    }

    public void setConditionTypeId(Long conditionTypeId) {
        this.conditionTypeId = conditionTypeId;
    }

    public Long getCategoryTypeId() {
        return this.categoryTypeId;
    }

    public void setCategoryTypeId(Long categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getItemSold() {
        return this.itemSold;
    }

    public void setItemSold(Boolean itemSold) {
        this.itemSold = itemSold;
    }

    public Boolean getItemRated() {
        return this.itemRated;
    }

    public void setItemRated(Boolean itemRated) {
        this.itemRated = itemRated;
    }

    public Long getRating() {
        return this.rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getImageSource() {
        return this.imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}