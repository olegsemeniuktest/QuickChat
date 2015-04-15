package com.oleg.chat.data.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by oleg on 09.03.2015.
 */
public abstract class AEntity {

    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public abstract String getCollectionName();

}
