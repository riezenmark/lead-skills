package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.omaewa.notastepik.domain.Review;
import org.omaewa.notastepik.service.api.ReviewService;
import org.omaewa.notastepik.util.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController extends AbstractRestController<Review, Long, ReviewService> {
    public ReviewController(ReviewService service) {
        super(service);
    }

    @Override
    @GetMapping
    @JsonView(Views.Review.class)
    public List<Review> list() {
        return service.list();
    }

    @Override
    @GetMapping("{id}")
    @JsonView(Views.Review.class)
    public Review getOne(@PathVariable("id") final Long id) {
        return service.getOne(id);
    }
}
