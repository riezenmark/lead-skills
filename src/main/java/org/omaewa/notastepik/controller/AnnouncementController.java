package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.util.Views;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController extends AbstractRestController<Announcement, Long, AnnouncementService> {
    public AnnouncementController(final AnnouncementService service) {
        super(service);
    }
}
