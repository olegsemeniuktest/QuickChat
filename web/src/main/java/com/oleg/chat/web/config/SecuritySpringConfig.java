package com.oleg.chat.web.config;

import com.oleg.chat.data.beans.Authority;
import com.oleg.chat.web.security.SingleNicknameAuthenticationProvider;
import com.oleg.chat.web.security.filters.NicknameFormLoginConfigurer;
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
    private NicknameFormLoginConfigurer nicknameFormLoginConfigurer;
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
                .antMatchers("/scripts/**").permitAll()

                .antMatchers("/signIn").anonymous()

                .antMatchers("/logout").authenticated()

                .antMatchers(
                        "/chats/private"
                ).hasAuthority(Authority.USER.name())

                .anyRequest()
                .permitAll()
                .and();

        http.apply(nicknameFormLoginConfigurer)
                .loginPage("/signIn")
                .failureUrl("/signIn?error=true")
                .nicknameParameter("nickname")
                .defaultSuccessUrl("/chats/public");


//        http.                //  http.apply() //addFilterBefore(nicknameAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).
//                formLogin()
//                .loginPage("/signIn")
//                .failureUrl("/signIn")
//                .usernameParameter("nickname")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/chats/public");

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/signIn");

    }

}
