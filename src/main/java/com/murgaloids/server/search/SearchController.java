package com.murgaloids.server.search;

import com.murgaloids.server.item.Item;
import com.murgaloids.server.item.ItemRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/search")
public class SearchController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public @ResponseBody
    List<Item> search(@NonNull @RequestParam(value = "query") String query) {
        return itemRepository.findByName(query);
    }
}
