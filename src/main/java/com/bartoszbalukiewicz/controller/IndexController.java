package com.bartoszbalukiewicz.controller;

import com.bartoszbalukiewicz.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by Bartek on 18.09.2016.
 */
@Controller
public class IndexController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping(value = "/login")
        public String login(@RequestParam Optional<String> error, Model model) {
        if(error.isPresent())
            model.addAttribute("error", true);
        return "login";
        }

}
