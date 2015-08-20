package com.oleg.chat.web.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This class represents authentication data of user with nickname
 * <p>
 * Created by Oleg Semeniuk on 07.06.2015.
 */
public class NicknameAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    /**
     * Use this constructor in cases where the {@link #isAuthenticated()} has to return 'false'
     *
     * @param principal
     */
    public NicknameAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
    }

    /**
     * Creates a token with the supplied array of authorities.
     * Use this constructor in cases where the {@link #isAuthenticated()} has to return 'true'
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     * @param principal
     */
    public NicknameAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use appropriate constructor");
        }

        super.setAuthenticated(false);
    }
}
