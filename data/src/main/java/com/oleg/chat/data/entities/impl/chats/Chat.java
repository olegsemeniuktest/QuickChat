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
@TypeAlias("public_chat")
public class Chat extends AChat implements IChat {

    @Override
    public boolean isPrivate() {
        return false;
    }

}
