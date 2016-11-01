package com.bartoszbalukiewicz.controller;

import org.owasp.appsensor.event.RestEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Bartek on 01.11.2016.
 */
@Controller
public class TestController {

    @Autowired
    private RestEventManager restEventManager;

    @GetMapping("/test/response")
    public void testResponses() {
        restEventManager.getResponses("0");
    }
}
