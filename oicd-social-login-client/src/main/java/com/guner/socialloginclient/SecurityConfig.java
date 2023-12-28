package com.guner.socialloginclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults()) // auth server login ekranına yönlendirme yapar, pom da spring-security-oauth2-client tanımı gerektirir
//                .formLogin(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // jwt'yi çözer.
                .build();
    }
}
