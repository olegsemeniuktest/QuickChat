package com.oleg.chat.web.security.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Map;

/**
 * Created by Oleg Semeniuk on 07.06.2015.
 */
@Component
public final class NicknameFormLoginConfigurer extends AbstractAuthenticationFilterConfigurer<HttpSecurity, NicknameFormLoginConfigurer, NicknameAuthenticationFilter> {


    public NicknameFormLoginConfigurer() {
        super(new NicknameAuthenticationFilter(), null);
    }

    /**
     * Specifies the URL to send users to if login is required.
     *
     * @param loginPage the login page to redirect to if authentication is required (i.e. "/login")
     * @return the {@link NicknameFormLoginConfigurer} for additional customization
     */
    public NicknameFormLoginConfigurer loginPage(String loginPage) {
        return super.loginPage(loginPage);
    }

    /**
     * The HTTP parameter to look for the username when performing authentication. Default
     * is "nickname".
     *
     * @param nickParameter the HTTP parameter to look for the nickname when performing authentication
     * @return the {@link NicknameFormLoginConfigurer} for additional customization
     */
    public NicknameFormLoginConfigurer nicknameParameter(String nickParameter) {
        getAuthenticationFilter().setNicknameParameter(nickParameter);
        return this;
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    private String getNicknameParameter() {
        return getAuthenticationFilter().getNicknameParameter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        try {
            super.configure(http);
        } catch (IllegalArgumentException e) {
            NicknameAuthenticationFilter authFilter = getAuthenticationFilter();
            http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

}