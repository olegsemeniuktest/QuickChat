package com.oleg.chat.data.entities.impl;

import com.oleg.chat.data.beans.Authority;
import com.oleg.chat.data.entities.AEntity;
import com.oleg.chat.data.entities.IUser;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents information about user which entered with unique nickname
 * Created by oleg on 08.03.2015.
 */
@Document(collection = User.COLLECTION_NAME)
public class User extends AEntity implements IUser {

    public static final String COLLECTION_NAME = "users";

    private String nickname;

    private Set<Authority> authorities = new HashSet<>();

    private boolean nonExpired = true;

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isNonExpired() {
        return nonExpired;
    }

    public void setNonExpired(boolean nonExpired) {
        this.nonExpired = nonExpired;
    }

}
