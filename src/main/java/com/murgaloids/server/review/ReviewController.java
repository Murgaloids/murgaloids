package com.murgaloids.server.review;

import com.murgaloids.server.JsonWrapper;
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
@RequestMapping(path="/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/add")
    public void addReview(@NonNull @RequestBody Review review) {
        if (!reviewRepository.existsById(review.getId()))
            reviewRepository.save(review);
    }

    @GetMapping("/get")
    public @ResponseBody
    JsonWrapper<Review> getReview(@NonNull @RequestParam Long id) {
        return new JsonWrapper<>(reviewRepository.existsById(id) ? reviewRepository.findById(id) : null);
    }

    @GetMapping("/all")
    public @ResponseBody JsonWrapper<Iterable<Review>> getReviews(@NonNull @RequestParam Long userId) {
        return new JsonWrapper<>(reviewRepository.findByRevieweeId(userId));
    }
}