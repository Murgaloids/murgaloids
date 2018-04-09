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

import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletResponse;

import com.murgaloids.server.JSONObject;
import com.murgaloids.server.student.StudentRepository;
import com.murgaloids.server.itemCondition.ItemConditionRepository;

@RestController
@RequestMapping(path="/items")
public class ItemController {
    @Autowired private ItemRepository itemRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ItemConditionRepository itemConditionRepository;

    @PostMapping("/add")
    public JSONObject addItem(@NonNull @RequestBody Item item, HttpServletResponse response) {
        Long itemId = item.getId();
        Long sellerId = item.getSellerId();
        Long conditionTypeId = item.getConditionTypeId();

        if (!itemRepository.existsById(itemId) &&
            studentRepository.existsById(sellerId) &&
            itemConditionRepository.existsById(conditionTypeId)) {
            itemRepository.save(item);
            response.setStatus(HttpServletResponse.SC_OK);
            return new JSONObject<>(item.getId(), HttpStatus.OK);
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new JSONObject<>(Long.valueOf(-1), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public void updateItem(@NonNull @RequestBody Item reqItem) {
        Item item = itemRepository.findById(reqItem.getId());
        if (item != null) {
            if (item.getSellerId() != reqItem.getSellerId())
                item.setSellerId(reqItem.getSellerId());
            if (item.getConditionTypeId() != reqItem.getConditionTypeId())
                item.setConditionTypeId(reqItem.getConditionTypeId());
            if (item.getCategoryTypeId() != reqItem.getCategoryTypeId())
                item.setCategoryTypeId(reqItem.getCategoryTypeId());
            if (!item.getItemName().equals(reqItem.getItemName()))
                item.setItemName(reqItem.getItemName());
            if (!item.getDescription().equals(reqItem.getDescription()))
                item.setDescription(reqItem.getDescription());
            if (item.getPrice() != reqItem.getPrice())
                item.setPrice(reqItem.getPrice());
            if (item.getItemSold() != reqItem.getItemSold())
                item.setItemSold(reqItem.getItemSold());

            itemRepository.save(item);
        }
    }

    @PostMapping("/delete")
    public void deleteItem(@NonNull @RequestParam Long id) {
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