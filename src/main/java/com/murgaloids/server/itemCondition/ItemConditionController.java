package com.murgaloids.server.itemCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/item-condition")
public class ItemConditionController {
    @Autowired
    private ItemConditionRepository itemConditionRepository;

    @GetMapping("/all")
    public @ResponseBody
    Iterable<ItemCondition> getAllItemConditions() {
        return itemConditionRepository.findAll();
    }
}
