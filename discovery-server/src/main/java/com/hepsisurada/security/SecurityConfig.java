package com.hepsisurada.security;
/* 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;*/
import org.springframework.context.annotation.Configuration;
@Configuration
public class SecurityConfig {
/*  @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and().formLogin();
         return http.build();
     }

     @Bean
     public UserDetailsService userDetailsService() {
         UserDetails user = User.withDefaultPasswordEncoder()
             .username("user")
             .password("password")
             .roles("USER")
             .build();
         return new InMemoryUserDetailsManager(user);
     }*/
}


/* 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    
    @Value("${eureka.username}")
    private String username;
    @Value("${eureka.password}")
    private String password;
    @Override
    public void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws exception{

        authenticationManagerBuilder.inMemoryAuthentication()
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .withUser(username)
        .password(password)
        .authorities("USER");

    }

    @Override
    public void configure(HttpSecurity httpSecurity){
        httpSecurity.csrf().disable()
        .authorizeRequests().anyRequest()
        .authenticated()
        .and()
        .httpBasic();



    
}*/


/* 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig  extends SecurityConfigurerAdapter {

    @Value("${eureka.username}")
    private String username;

    @Value("${eureka.password}")
    private String password;

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(username)
                .password(encoder().encode(password))
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Configuration
    public static class WebSecurityConfigAdapter {
*
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();
        }
     } 
}*/
