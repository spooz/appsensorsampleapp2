package com.bartoszbalukiewicz.controller;

import com.bartoszbalukiewicz.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Bartek on 11.11.2016.
 */
@Controller
public class AdminController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/topic/{id}/delete")
    public void deleteTopic(@PathVariable Long id) {
        forumService.deleteTopic(id);
    }


}
