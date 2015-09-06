package com.oleg.chat.web.security;

import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by Oleg Semeniuk on 02.05.2015.
 */
@Component
public class SingleNicknameAuthenticationProvider implements AuthenticationProvider {

    Logger log = LoggerFactory.getLogger(SingleNicknameAuthenticationProvider.class);

    @Autowired
    private UserManager userManager;
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(NicknameAuthenticationToken.class, authentication, messages.getMessage(
                "SingleNicknameAuthenticationProvider.onlySupports",
                "Only NicknameAuthenticationToken is supported"));

        String nickname = authentication.getPrincipal().toString();
        IUser user = userManager.createNew(nickname);
        Assert.notNull(user, "Created user is null - a violation of the interface contract");

        afterUserCreationChecks(user);
        return createSuccessAuthentication(user, authentication, user);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return NicknameAuthenticationToken.class.isAssignableFrom(authentication);
    }

    protected void afterUserCreationChecks(IUser userData) throws AuthenticationException {
        if (userData.getAuthorities().isEmpty()) {
            log.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage(
                    "SingleNicknameAuthenticationProvider.authenticationFailed",
                    "Authentication failed"));
        }

        if (userData.getNickname().isEmpty()) {
            log.debug("Authentication failed: user nickname is empty");
            throw new BadCredentialsException(messages.getMessage(
                    "SingleNicknameAuthenticationProvider.authenticationFailed",
                    "Authentication failed"));
        }
    }

    /**
     * Creates a successful {@link Authentication} object.
     *
     * @param principal      that should be the principal in the returned object
     * @param authentication that was presented to the provider for validation
     * @param user           that was loaded by the implementation
     * @return the successful authentication token
     */
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, IUser user) {
        NicknameAuthenticationToken result = new NicknameAuthenticationToken(principal, user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }
}
