package com.oleg.chat.data.entities.impl.chats;

import com.oleg.chat.data.entities.AEntity;
import com.oleg.chat.data.entities.IChat;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.Message;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Created by oleg on 08.03.2015.
 */
@Document(collection = AChat.COLLECTION_NAME)
public abstract class AChat extends AEntity implements IChat {
    public static final String COLLECTION_NAME = "chats";

    private String name;

    private List<Message> messages = new ArrayList<>();

    private Set<IUser> activeUsers = new HashSet<>();

    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public Collection<IUser> getActiveUsers() {
        return activeUsers;
    }

    @Override
    public void addActiveUser(IUser user) {
        activeUsers.add(user);
    }

    @Override
    public void removeActiveUser(IUser user) {
        activeUsers.remove(user);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

}
