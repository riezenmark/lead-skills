package org.omaewa.notastepik.controller;

import org.omaewa.notastepik.domain.Timetable;
import org.omaewa.notastepik.service.api.TimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController extends AbstractRestController<Timetable, Long, TimetableService> {
    public TimetableController(final TimetableService service) {
        super(service);
    }
}
