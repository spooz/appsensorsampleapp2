package com.bartoszbalukiewicz.controller;

import com.bartoszbalukiewicz.form.RegisterForm;
import com.bartoszbalukiewicz.form.validator.RegisterFormValidator;
import com.bartoszbalukiewicz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by postgres on 2016-09-20.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterFormValidator registerFormValidator;

    @InitBinder("registerForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerFormValidator);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "register";

        userService.registerUser(registerForm);

        return "redirect:/login";

    }


}
