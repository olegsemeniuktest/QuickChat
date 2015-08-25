package com.oleg.chat.data.entities;

import org.springframework.data.annotation.Id;

/**
 * Created by oleg on 09.03.2015.
 */
public abstract class AEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract String getCollectionName();

}
