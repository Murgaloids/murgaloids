package com.murgaloids.server.item;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/add")
    public void addItem(@NonNull @RequestBody Item item) {
        if (!itemRepository.existsById(item.getId()))
            itemRepository.save(item);
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

    @GetMapping("/all")
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }
}