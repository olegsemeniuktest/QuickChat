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
public class SignInController {

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn(ModelMap model, Principal principal) {
        if (principal != null) {
            return "redirect:/chats/public";
        } else {
            return "signIn";
        }
    }

    @RequestMapping("")
    public String root() {
        return "redirect:/signIn";
    }
}
