package com.oleg.chat.data.entities;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Oleg Semeniuk.
 */
public interface IUser {

    Long getId();

    String getNickname();

    Collection<? extends GrantedAuthority> getAuthorities();

    /**
     * Indicates whether the user's account has expired.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    boolean isNonExpired();
}
