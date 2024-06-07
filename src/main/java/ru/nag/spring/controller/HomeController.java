package ru.nag.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rest/home")
public class HomeController {

    @ResponseBody
    @GetMapping
    public String hello(){
        return "hello world";
    }

}
