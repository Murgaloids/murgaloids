package com.murgaloids.server.item;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

/**
 * Item is a class representation of an item.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @NonNull
    @Column(name = "seller_id", columnDefinition = "int(11)")
    private Long sellerId;

    @NonNull
    @Column(name = "condition_type_id", columnDefinition = "int(11)")
    private Long conditionTypeId;

    @NonNull
    @Column(name = "category_type_id", columnDefinition = "int(11)")
    private Long categoryTypeId;

    @NonNull
    @Column(name = "item_name", columnDefinition = "varchar(254)")
    private String itemName;

    @NonNull
    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;

    @NonNull
    @Column(name = "price", columnDefinition = "double(10,2)")
    private Double price;

    @NonNull
    @Column(name = "is_item_sold", columnDefinition = "tinyint(1)")
    private Boolean itemSold;

    @NonNull
    @Column(name = "is_item_rated", columnDefinition = "tinyint(1)")
    private Boolean itemRated;

    @Column(name = "rating", columnDefinition = "int(11)")
    private Long rating;

    @NonNull
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "date_added", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
}