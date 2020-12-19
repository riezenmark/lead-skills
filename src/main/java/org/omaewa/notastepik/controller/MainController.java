package org.omaewa.notastepik.controller;

import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.domain.User;
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
    @GetMapping
    public String initClientData(final Model model, @AuthenticationPrincipal final User user) {
        HashMap<Object, Object> clientData = new HashMap<>();
        if (user != null) {
            clientData.put("profile", user);
            //todo добавленные курсы, расписание
            //clientData.put("addedCars", carService.getCarsOfUserWithId(user.getId()));
        }
        //todo subjects
        //clientData.put("makers", makerService.getMakers(null));
        model.addAttribute("clientData", clientData);
        return "index";
    }
}
