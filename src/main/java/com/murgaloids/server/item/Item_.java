package com.murgaloids.server.item;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Item.class)
public class Item_ {
    public static volatile SingularAttribute<Item, Long> id;
    public static volatile SingularAttribute<Item, Long> sellerId;
    public static volatile SingularAttribute<Item, Long> conditionTypeId;
    public static volatile SingularAttribute<Item, Long> categoryTypeId;
    public static volatile SingularAttribute<Item, String> itemName;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile SingularAttribute<Item, Double> price;
    public static volatile SingularAttribute<Item, Boolean> itemSold;
    public static volatile SingularAttribute<Item, Boolean> itemRated;
    public static volatile SingularAttribute<Item, Long> rating;
    public static volatile SingularAttribute<Item, Date> dateAdded;
}
