package com.murgaloids.server.item;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/add")
    public long addItem(@NonNull @RequestBody Item item) {
        if (!itemRepository.existsById(item.getId())) {
            itemRepository.save(item);
            return item.getId();
        }
        return -1;
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
    public @ResponseBody Item getItem(@NonNull @RequestParam Long id) {
        return itemRepository.existsById(id) ? itemRepository.findById(id) : null;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Item> getItems(@NonNull @RequestParam Long userId) {
        return itemRepository.findBySellerId(userId);
    }

    @GetMapping("/recent")
    public @ResponseBody Iterable<Item> getRecentItems(int numOfResults) {
        return itemRepository.findRecentItems(numOfResults);
    }
}