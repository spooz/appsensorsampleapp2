package com.bartoszbalukiewicz.controller;

        import com.bartoszbalukiewicz.service.ForumService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;

/**
 * Created by postgres on 2016-09-20.
         */
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;
}
