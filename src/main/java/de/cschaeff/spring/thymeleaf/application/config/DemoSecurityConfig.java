package de.cschaeff.spring.thymeleaf.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
                //Für untenstehende Konfiguration benötigt man mind. die Rolle EMPLOYEE und MANAGER
                .withUser(users.username("mary").password("test123").roles("MANAGER,EMPLOYEE"))
                .withUser(users.username("susan").password("test123").roles("ADMIN"));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().authenticated()
                //.antMatchers("/").hasRole("EMPLOYEE")
                //.antMatchers("/partner/**").hasRole("MANAGER") --> Um auf Rollenebene zu schützen
                .and()
                .formLogin()
                    .loginPage("/showMyLoginPage")
                    .failureUrl("/showMyFailurePage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                .and()
                    .logout().permitAll()//Zum Ausloggen --> /logout
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied");



    }
}
