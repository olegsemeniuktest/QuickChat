package com.oleg.chat.data.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by oleg on 08.03.2015.
 */
@Document(collection = User.COLLECTION_NAME)
public class User extends AEntity {

    public static final String COLLECTION_NAME = "users";

    private long id;
    private String login;

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }
}
