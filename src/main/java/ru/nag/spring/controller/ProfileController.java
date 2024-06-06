package ru.nag.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.service.CustomUserDetailsService;

import java.util.UUID;


@Controller
public class ProfileController {

    private final CustomUserDetailsService userService;

    @Autowired
    public ProfileController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable("id") UUID id,
                             Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.getUserById(id));
        return "/profile/profile";
    }

}
