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
@RequestMapping(value = "/chats")
public class ChatListController {

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String publicChats(ModelMap model) {
        return "publicChatsList";
    }

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String privateChats(ModelMap model) {
        return "privateChatsList";
    }
}
