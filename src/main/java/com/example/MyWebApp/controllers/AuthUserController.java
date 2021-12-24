package com.example.MyWebApp.controllers;

import com.example.MyWebApp.entity.*;
import com.example.MyWebApp.service.MyappUserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthUserController {

    private final MyappUserRegister registrationService;

    @Autowired
    public AuthUserController(MyappUserRegister myappUserRegister) {
        this.registrationService = myappUserRegister;
    }

    @GetMapping("/")
    public String handleSignIn(){
        return "signin";
    }

    @GetMapping("/signup")
    public String handleSignUp(RegistrationForm registrationForm,Model model){
        model.addAttribute("reg", new RegistrationForm());
        return "signup";
    }

    @PostMapping("/login")
    @ResponseBody
    public ContactConfirmationResponse handleLogin(ContactConfirmationPayload payload){
        ContactConfirmationResponse loginResponse = registrationService.login(payload);
        return loginResponse;
    }

    @PostMapping("/reg")
    public String handleUserRegistration(@Valid RegistrationForm registrationForm, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("reg",new RegistrationForm());
            return "redirect:/signup";
        }else {
            registrationService.registerNewUser(registrationForm);
            return "redirect:/";
        }
    }








}
