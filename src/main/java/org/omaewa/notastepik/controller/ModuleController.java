package org.omaewa.notastepik.controller;

import org.omaewa.notastepik.domain.Module;
import org.omaewa.notastepik.service.api.ModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/module")
public class ModuleController extends AbstractRestController<Module, Long, ModuleService> {
    public ModuleController(final ModuleService service) {
        super(service);
    }
}
