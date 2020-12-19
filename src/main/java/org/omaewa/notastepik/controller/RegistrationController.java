package org.omaewa.notastepik.controller;

import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.domain.User;
import org.omaewa.notastepik.service.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String registration() {
        return "signup";
    }

    @PostMapping
    public String addUser(final User user, final Map<String, Object> model) {
        String page = "signup";
        if (!userService.userWithNameExists(user)) {
            userService.add(user);
            page = "redirect:/login";
        }
        return page;
    }
}
