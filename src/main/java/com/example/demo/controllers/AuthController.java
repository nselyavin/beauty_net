package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String signinForm(@ModelAttribute("user") User user){
        //response.setHeader("Content-Type","text/html");
        return "auth/signin";
    }

//    @PostMapping(value = "/")
//    public String signin(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//
//        return "redirect:/news";
//    }

    @GetMapping(value = "/signup")
    public String signupForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        return "auth/signup";
    }


    @PostMapping(value = "/signup")
    public String createUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            // Неправильно введенны данные
            model.addAttribute("bindingError", "Ошибка данных");
            return "auth/signup";
        }

        User user = userService.createUser(userDTO);

        return "redirect:/auth";
    }

    @GetMapping(value = "/recovery")
    public String recoveryPass(){
        //response.setHeader("Content-Type","text/html");
        return "/auth/recovery";
    }

    @PostMapping(value = "/recovery")
    public String sendRecoveryCode(){
        //response.setHeader("Content-Type","text/html");
        // ToDo Редирект на страницу ввода кода востановления
        return "redirect:/";
    }

}
