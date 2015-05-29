package com.oleg.chat.web.config;

import com.oleg.chat.data.services.impl.UserService;
import com.oleg.chat.web.security.SingleNicknameAuthenticationProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by Oleg Semeniuk on 18.02.2015.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.oleg.chat.web.security")
public class SecuritySpringConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userDetailsService;
    @Resource
    private SingleNicknameAuthenticationProvider authenticationProvider;


    @Resource
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
//        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                   http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/scripts/**").                     permitAll()

                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/signIn")
                .failureUrl("/signIn")
                .usernameParameter("nickname")
                .passwordParameter("password")
                .defaultSuccessUrl("/chats/public");

        http.logout()
                // ��������� ������ ������ ����
                .permitAll()
                        // ��������� URL �������
                .logoutUrl("/logout")
                        // ��������� URL ��� ������� �������
                .logoutSuccessUrl("/signIn");

    }

}
