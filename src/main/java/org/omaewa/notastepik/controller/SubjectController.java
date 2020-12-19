package org.omaewa.notastepik.controller;

import org.omaewa.notastepik.domain.Subject;
import org.omaewa.notastepik.service.api.SubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController extends AbstractRestController<Subject, Integer, SubjectService> {
    public SubjectController(final SubjectService service) {
        super(service);
    }
}
