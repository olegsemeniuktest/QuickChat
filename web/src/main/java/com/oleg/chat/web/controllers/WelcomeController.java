package com.oleg.chat.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Oleg Semeniuk on 19.02.2015.
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(ModelMap model, Principal principal) {
        if (principal != null) {
            return "startPage";
        } else {
            return "welcome";
        }
    }

    @RequestMapping("")
    public String home() {
        return "redirect:/welcome";
    }
}
