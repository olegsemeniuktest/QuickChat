package com.oleg.chat.data.entities;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by olegsemeniuk on 02.05.2015.
 */
public interface IUser {

    long getId();

    String getNickname();

    Collection<? extends GrantedAuthority> getAuthorities();
}
