package com.Tanisht.usermanagement.usermanage.Config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
public PasswordEncoder encoder (){
    return new BCryptPasswordEncoder();
}
@Bean
public UserDetailsService userdetailsService (){
    return new CustomUserDetailsService();
}
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userdetailsService());

        daoAuthenticationProvider.setPasswordEncoder(encoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize// Allow access to static resources
                        .requestMatchers("login", "signup").permitAll() // Permit access to login and signup
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")// Require ADMIN role for /admin/**
                        .requestMatchers("/user/**").hasAnyRole("USER")
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formlogin-> formlogin.defaultSuccessUrl("/home"));

        return http.build();
    }

}
