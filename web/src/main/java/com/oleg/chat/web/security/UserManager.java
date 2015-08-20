package com.oleg.chat.web.security;

import com.oleg.chat.data.beans.Authority;
import com.oleg.chat.data.entities.IUser;
import com.oleg.chat.data.entities.impl.User;
import com.oleg.chat.data.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by Oleg Semeniuk.
 */
@Component
public class UserManager {

    @Autowired
    private IUserService userService;
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private static final Pattern NICK_PATTERN = Pattern.compile("^[\\p{L}0-9][\\p{L}0-9_-]{4,19}$"); // Pattern.compile("^[a-zA-Z�-��-�0-9][a-zA-Z�-��-�0-9_-]{4,19}$");

    /**
     * Check if there are user with the same nickname
     *
     * @param name - user nickname to check
     * @return true, if specified nickname isn't used
     */
    public boolean isFree(String name) {
        return !userService.getByNickName(name).isPresent();
    }

    /**
     * Check nickname for unique and matching rules
     *
     * @param nickname
     * @throws AuthenticationException if specified nickname isn't valid
     */
    public void validateNickname(String nickname) throws AuthenticationException {
        if (!NICK_PATTERN.matcher(nickname).matches()) {
            throw new BadCredentialsException(messages.getMessage("UserManaging.nicknameBadFormat", "The nickname should be up 5 to 20 characters and start with a letter"));
        }
        if (!isFree(nickname)) {
            throw new BadCredentialsException(messages.getMessage("UserManaging.nicknameIsUsed", "This nickname is already in use"));
        }
    }

    public synchronized IUser createNew(String nickname) throws AuthenticationException {
        validateNickname(nickname);
        User user = newUserObject(nickname);
        userService.insert(user);
        return user;
    }

    private User newUserObject(String nickname) {
        User user = new User();
        user.setNickname(nickname);
        user.addAuthority(Authority.USER);
        return user;
    }
}
