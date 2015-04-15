package com.oleg.chat.data.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 08.03.2015.
 */
@Document(collection = Chat.COLLECTION_NAME)
@TypeAlias("chat")
public class Chat extends AEntity {
    public static final String COLLECTION_NAME = "chats";

    private String name;

//    private List<Message> messages = new ArrayList<>();

    private List<User> activeUsers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(List<User> activeUsers) {
        this.activeUsers = activeUsers;
    }

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

}
