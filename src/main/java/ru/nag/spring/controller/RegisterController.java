package ru.nag.spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nag.spring.entity.User;
import ru.nag.spring.exception.UserAlreadyExistsException;
import ru.nag.spring.service.CustomUserDetailsService;


@Controller
public class RegisterController {

    private final CustomUserDetailsService userService;

    @Autowired
    public RegisterController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "/main/register";
    }

    @PostMapping("/register")
    public String toRegister(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult) throws UserAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            return "/main/register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

}