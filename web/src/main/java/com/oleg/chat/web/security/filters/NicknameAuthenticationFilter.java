package com.oleg.chat.web.security.filters;

import com.oleg.chat.web.security.NicknameAuthenticationToken;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olegsemeniuk on 06.05.2015.
 */
public class NicknameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String NICKNAME_KEY = "nickname";
    public static final String DEFAULT_URL_PATTERN = "/signIn";

    private String nicknameParameter = NICKNAME_KEY;
    private RequestMethod processingRequestsMethod = RequestMethod.POST;

    public NicknameAuthenticationFilter() {
        super(new AntPathRequestMatcher(DEFAULT_URL_PATTERN, "POST"));
    }


    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(processingRequestsMethod.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String nickname = obtainNickname(request);
        if (nickname == null) {
            nickname = "";
        }
        nickname = nickname.trim();

        NicknameAuthenticationToken authCandidate = new NicknameAuthenticationToken(nickname);

        setDetails(request, authCandidate);
        return this.getAuthenticationManager().authenticate(authCandidate);
    }

    private String obtainNickname(HttpServletRequest request) {
        return request.getParameter(nicknameParameter);
    }

    private void setDetails(HttpServletRequest request, NicknameAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the nickname from the login
     * request.
     *
     * @param newNickParameter the parameter name. Defaults to "nickname".
     */
    public void setNicknameParameter(String newNickParameter) {
        Assert.hasText(newNickParameter, "Nickname parameter must not be empty or null");
        this.nicknameParameter = newNickParameter;
    }

    /**
     * @return the parameter name which using to obtain the nickname from the login request.
     */
    public final String getNicknameParameter() {
        return nicknameParameter;
    }

    public void setProcessingRequestsMethod(RequestMethod processingRequestsMethod) {
        this.processingRequestsMethod = processingRequestsMethod;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(DEFAULT_URL_PATTERN, processingRequestsMethod.name()));
    }

}
