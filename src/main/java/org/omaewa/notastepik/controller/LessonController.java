package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.omaewa.notastepik.domain.Lesson;
import org.omaewa.notastepik.service.api.LessonService;
import org.omaewa.notastepik.util.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
public class LessonController extends AbstractRestController<Lesson, Long, LessonService> {
    public LessonController(final LessonService service) {
        super(service);
    }

    @Override
    @GetMapping
    @JsonView(Views.Lesson.class)
    public List<Lesson> list() {
        return service.list();
    }

    @Override
    @GetMapping("{id}")
    @JsonView(Views.Lesson.class)
    public Lesson getOne(@PathVariable("id") final Long id) {
        return service.getOne(id);
    }
}
