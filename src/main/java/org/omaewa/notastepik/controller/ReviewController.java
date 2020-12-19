package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.omaewa.notastepik.domain.Review;
import org.omaewa.notastepik.service.api.ReviewService;
import org.omaewa.notastepik.util.Views;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController extends AbstractRestController<Review, Long, ReviewService> {
    public ReviewController(ReviewService service) {
        super(service);
    }

    @Override
    @GetMapping
    @JsonView(Views.Review.class)
    public Page<Review> list(@PageableDefault final Pageable pageable) {
        return service.getPage(pageable);
    }

    @Override
    @GetMapping("{id}")
    @JsonView(Views.Review.class)
    public Review getOne(@PathVariable("id") final Long id) {
        return service.getOne(id);
    }
}
