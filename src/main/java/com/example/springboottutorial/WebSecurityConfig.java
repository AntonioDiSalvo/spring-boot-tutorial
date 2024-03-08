package com.example.springboottutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disabilitare la gestione dei filtri CORS e CSRF
//        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);

        // escludere dall'autenticazione i percorsi relativi a login e creazione utente
        String[] EXCLUDED_URL = {
          "/",
          "/index.html",
          "/error",
          "/webjars/**",
                "/social/user"
        };

        http.authorizeHttpRequests(auth -> auth.requestMatchers(EXCLUDED_URL).permitAll());

        // richiedere l'autenticazione per tutti gli altri mapping
        //http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.oauth2Login(Customizer.withDefaults());
        //http.oauth2Client()
        return http.build();
    }
}
