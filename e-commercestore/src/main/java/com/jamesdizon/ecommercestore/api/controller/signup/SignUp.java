package com.jamesdizon.ecommercestore.api.controller.signup;

import com.jamesdizon.ecommercestore.api.model.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUp {

    @GetMapping("/registerform")
    public String greetingForm(Model model) {
        model.addAttribute("SignUpForm", new SignUpForm());
        return "register";
    }

    @PostMapping("/registerform")
    public String greetingSubmit(@ModelAttribute SignUpForm SignUpForm, Model model) {
        model.addAttribute("SignUpForm", SignUpForm);
        return "result";
    }

}

