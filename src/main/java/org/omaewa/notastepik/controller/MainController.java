package org.omaewa.notastepik.controller;

import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.domain.User;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final AnnouncementService announcementService;

    @GetMapping
    public String initClientData(final Model model, @AuthenticationPrincipal final User user) {
        HashMap<Object, Object> clientData = new HashMap<>();
        if (user != null) {
            clientData.put("profile", user);
            clientData.put(
                    "addedAnnouncements",
                    announcementService.getAnnouncementsOfUserWithId(user.getId())
            );
        }
        model.addAttribute("clientData", clientData);
        return "index";
    }
}
