package org.omaewa.notastepik.controller;

import org.omaewa.notastepik.domain.User;
import org.omaewa.notastepik.service.api.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractRestController<User, String, UserService> {
    public UserController(final UserService service) {
        super(service);
    }
}
