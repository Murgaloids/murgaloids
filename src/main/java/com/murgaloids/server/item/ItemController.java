package com.murgaloids.server.item;

import com.murgaloids.server.JsonWrapper;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path="/items")
public class ItemController {
    @Autowired private ItemRepository itemRepository;

    @PostMapping("/add")
    public long addItem(@NonNull @RequestBody Item item) {
        if (!itemRepository.existsById(item.getId())) {
            itemRepository.save(item);
            return item.getId();
        }
        return -1;
    }

    @PostMapping("/update")
    public JsonWrapper<Item> updateItem(@NonNull @RequestBody Item reqItem) {
        Item item = itemRepository.findById(reqItem.getId());
        if (item != null) {
            if ((item.getConditionTypeId() == null) || (reqItem.getConditionTypeId() != null) &&
                (item.getConditionTypeId() != reqItem.getConditionTypeId()))
                item.setConditionTypeId(reqItem.getConditionTypeId());
            if ((item.getCategoryTypeId() == null) || (reqItem.getCategoryTypeId() != null) &&
                (item.getCategoryTypeId() != reqItem.getCategoryTypeId()))
                item.setCategoryTypeId(reqItem.getCategoryTypeId());
            if ((item.getItemName() == null) || (reqItem.getItemName() != null) &&
                (!item.getItemName().equals(reqItem.getItemName())))
                item.setItemName(reqItem.getItemName());
            if ((item.getDescription() == null) || (reqItem.getDescription() != null) &&
                (!item.getDescription().equals(reqItem.getDescription())))
                item.setDescription(reqItem.getDescription());
            if ((item.getPrice() == null) || (reqItem.getPrice() != null) &&
                (item.getPrice() != reqItem.getPrice()))
                item.setPrice(reqItem.getPrice());
            if ((item.getItemSold() == null) || (reqItem.getItemSold() != null) &&
                (item.getItemSold() != reqItem.getItemSold()))
                item.setItemSold(reqItem.getItemSold());
            if ((item.getImageSource() == null) || (reqItem.getImageSource() != null) &&
                (item.getImageSource() != reqItem.getImageSource()))
                item.setImageSource(reqItem.getImageSource());

            itemRepository.save(item);
            return new JsonWrapper<>(item);
        }
        return null;
    }

    @PostMapping("/delete")
    public void deleteItem(@NonNull @RequestBody Long id) {
        if(itemRepository.existsById(id))
            itemRepository.delete(itemRepository.findById(id));
    }

    @GetMapping("/get")
    public @ResponseBody
    JsonWrapper<Item> getItem(@NonNull @RequestParam Long id) {
        return new JsonWrapper<>(itemRepository.existsById(id) ? itemRepository.findById(id) : null);
    }

    @GetMapping("/all")
    public @ResponseBody JsonWrapper<Iterable<Item>> getItems(@NonNull @RequestParam Long userId) {
        return new JsonWrapper<>(itemRepository.findBySellerId(userId));
    }

    @GetMapping("/recent")
    public @ResponseBody JsonWrapper<Iterable<Item>> getRecentItems(int numOfResults) {
        return new JsonWrapper<>(itemRepository.findRecentItems(numOfResults));
    }

    @GetMapping("/search")
    public @ResponseBody
    JsonWrapper<Iterable<Item>> search(
            @NonNull @RequestParam(value = "query") String query,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "priceOption", required = false) String priceOption,
            @RequestParam(value = "sellerId", required = false) Long sellerId,
            @RequestParam(value = "description", required = false) String description
    ) {
        if (priceOption != null && priceOption.equals("gte")) {
            return new JsonWrapper<>(
                    itemRepository.findAll(
                            Specifications
                                    .where(ItemSpecifications.withItemName(query))
                                    .and(ItemSpecifications.withPriceGreaterThanOrEqualTo(price))
                                    .and(ItemSpecifications.withSellerId(sellerId))
                                    .and(ItemSpecifications.withDescription(description))
                    )
            );
        }
        else if (priceOption != null && priceOption.equals("lte")) {
            return new JsonWrapper<>(
                    itemRepository.findAll(
                            Specifications
                                    .where(ItemSpecifications.withItemName(query))
                                    .and(ItemSpecifications.withPriceLessThanOrEqualTo(price))
                                    .and(ItemSpecifications.withSellerId(sellerId))
                                    .and(ItemSpecifications.withDescription(description))
                    )
            );
        }
        else {
            return new JsonWrapper<>(
                    itemRepository.findAll(
                            Specifications
                                    .where(ItemSpecifications.withItemName(query))
                                    .and(ItemSpecifications.withPriceEqualTo(price))
                                    .and(ItemSpecifications.withSellerId(sellerId))
                                    .and(ItemSpecifications.withDescription(description))
                    )
            );
        }
    }
}
