package com.oleg.chat.data.entities.impl;

import com.oleg.chat.data.entities.AEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by oleg on 08.03.2015.
 */
@Document(collection = Message.COLLECTION_NAME)
public class Message extends AEntity {

    public static final String COLLECTION_NAME = "messages";

    private long id;
    private String text;
    private User sender;
    private Date sentTime;


    private Chat chat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }
}
