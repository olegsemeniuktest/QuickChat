package com.oleg.chat.data.entities.impl.chats;

import com.oleg.chat.data.entities.impl.User;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 08.03.2015.
 */
@TypeAlias("private_chat")
public class PrivateChat extends AChat {

    private List<User> members = new ArrayList<>();

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @Override
    public boolean isPrivate() {
        return true;
    }
}
