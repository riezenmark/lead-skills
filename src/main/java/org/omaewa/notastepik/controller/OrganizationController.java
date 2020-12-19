package org.omaewa.notastepik.controller;

import org.omaewa.notastepik.domain.Organization;
import org.omaewa.notastepik.service.api.OrganizationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController extends AbstractRestController<Organization, Long, OrganizationService> {
    public OrganizationController(final OrganizationService service) {
        super(service);
    }
}
