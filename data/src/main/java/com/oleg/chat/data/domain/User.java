package com.oleg.chat.data.domain;

import com.oleg.chat.data.beans.Authority;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oleg on 08.03.2015.
 */
@Document(collection = User.COLLECTION_NAME)
public class User extends AEntity implements IUser, UserDetails {

    public static final String COLLECTION_NAME = "users";

    private String nickname;

    private Set<Authority> authorities = new HashSet<>();

    private Boolean enabled = Boolean.TRUE;

    private Boolean accountNonExpired = Boolean.TRUE;

    private Boolean accountNonLocked = Boolean.TRUE;

    private Boolean credentialsNonExpired = Boolean.TRUE;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> grAuthorities = new ArrayList();
        for (Authority authority : authorities) {
            grAuthorities.add(new SimpleGrantedAuthority(authority.name()));
        }
        return grAuthorities;
    }

    @Override
    public String getPassword() {
        return nickname;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
