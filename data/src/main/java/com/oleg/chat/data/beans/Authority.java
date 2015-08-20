package com.oleg.chat.data.beans;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Oleg Semeniuk on 13.11.2014.
 */
public enum Authority implements GrantedAuthority {

    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
