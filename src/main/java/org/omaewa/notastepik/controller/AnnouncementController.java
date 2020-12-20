package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.domain.AnnouncementType;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.util.Views;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService service;

    @GetMapping
    @JsonView(Views.IdRepresentation.class)
    public List<Announcement> list(
            @RequestParam(required = false) final String q,
            @RequestParam(required = false) final AnnouncementType type
    ) {
        return service.getAnnouncements(q, type);
    }

    @GetMapping("{id}")
    @JsonView(Views.IdRepresentation.class)
    public Announcement getOne(@PathVariable("id") final Long id) {
        return service.getOne(id);
    }

    @PostMapping
    public Announcement add(@RequestBody final Announcement announcement) {
        return service.add(announcement);
    }

    @PutMapping("{id}")
    public Announcement update(@PathVariable("id") final Long id, @RequestBody final Announcement announcement) {
        return service.update(id, announcement);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") final Long id) {
        service.delete(id);
    }
}
