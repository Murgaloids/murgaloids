package com.murgaloids.server.bookmark;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path="/bookmarks")
public class BookmarkController {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @PostMapping("/add")
    public void addBookmark(@NonNull @RequestBody Bookmark bookmark) {
        if (!bookmarkRepository.existsById(bookmark.getId()))
            bookmarkRepository.save(bookmark);
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<Bookmark> getBookmarks(@NonNull @RequestParam Long userId) {
        return bookmarkRepository.findByBuyerId(userId);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }
}