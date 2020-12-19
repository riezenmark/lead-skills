package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.omaewa.notastepik.domain.Module;
import org.omaewa.notastepik.service.api.ModuleService;
import org.omaewa.notastepik.util.Views;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/module")
public class ModuleController extends AbstractRestController<Module, Long, ModuleService> {
    public ModuleController(final ModuleService service) {
        super(service);
    }
}
