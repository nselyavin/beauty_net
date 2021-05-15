package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String signinForm(@ModelAttribute("user") User user){
        //response.setHeader("Content-Type","text/html");
        return "auth/signin";
    }

    @PostMapping(value = "/")
    public String signin(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "auth/signin";
        }

        return "/news";
    }

    @GetMapping(value = "/signup")
    public String signupForm(@ModelAttribute("user") User user){
        return "auth/signup";
    }

    @PostMapping(value = "/signup")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "auth/signup";
        }

        userService.createUser(user);

        return "redirect:/";
    }

    @GetMapping(value = "/recovery")
    public String recoveryPass(){
        //response.setHeader("Content-Type","text/html");
        return "redirect:/";
    }

}
