package ru.nag.spring.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nag.spring.entity.User;
import ru.nag.spring.exception.WrongPasswordException;
import ru.nag.spring.service.CustomUserDetailsService;


@Controller
public class LoginController {

    private final CustomUserDetailsService userService;

    @Autowired
    public LoginController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new User());
        return "/main/login";
    }

    @PostMapping("/login")
    public String toLogin(@Valid @ModelAttribute("user") User user) {
//        UserDetails foundUser = userService.loadUserByUsername(user.getEmail());
//        System.out.println(user.getPassword());
//        System.out.println(foundUser.getPassword());
//        if (!userService.CheckPassword(user.getPassword(), foundUser.getPassword())) {
//            throw new WrongPasswordException("Password incorrect");
//        }
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String toLogout() {
        return "/main/main";
    }
}
