package com.bartoszbalukiewicz.controller;

import com.bartoszbalukiewicz.service.ForumService;
import com.bartoszbalukiewicz.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Bartek on 11.11.2016.
 */
@Controller
public class AdminController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/topic/{id}/delete")
    public String deleteTopic(@PathVariable Long id) {
        forumService.deleteTopic(id);
        return "redirect:/";
    }

    @GetMapping("/message/{id}/delete")
    public void deleteMessage(@PathVariable Long id) {
        forumService.deleteMessage(id);}


    @PreAuthorize("@securityService.isAdmin()")
    @GetMapping("/admin")
    public String getAdminPage(Model model) {

        model.addAttribute("notifications", notificationService.findAll());

        return "admin";
    }

}
