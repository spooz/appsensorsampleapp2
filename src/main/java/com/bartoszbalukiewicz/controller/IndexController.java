package com.bartoszbalukiewicz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Bartek on 18.09.2016.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/topic/{topicId}/messages")
    public String messages (@PathVariable("topicId") Long topicId, Model model){
        model.addAttribute("topicId", topicId);
        return "messages";
    }

    @GetMapping("/login")
        public String login() {
            return "login";
        }

}
