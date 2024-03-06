package com.example.springboottutorial;

import com.example.springboottutorial.filter.JWTAuthenticationFilter;
import com.example.springboottutorial.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(
        prePostEnabled = true, // (1)
        securedEnabled = true, // (2)
        jsr250Enabled = true) // (3)
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager getAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return new ProviderManager(provider);
    }

    private JWTAuthenticationFilter getJWTAuthenticationFilter() {
        return new JWTAuthenticationFilter(getAuthenticationManager());
    }

    private JWTAuthorizationFilter getJWTAuthorizationFilter() {
        return new JWTAuthorizationFilter(getAuthenticationManager());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disabilitare la gestione dei filtri CORS e CSRF
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);

        // escludere dall'autenticazione i percorsi relativi a login e creazione utente
        String[] EXCLUDED_URL = {
          "/user",
          "/login"
        };
        http.authorizeHttpRequests(auth -> auth.requestMatchers(EXCLUDED_URL).permitAll());

        // richiedere l'autenticazione per tutti gli altri mapping
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // gestione stateless della sessione
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // configurare l'esecuzione dei filtri definiti
        http.addFilter(this.getJWTAuthenticationFilter());
        http.addFilter(this.getJWTAuthorizationFilter());

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 =
//                User.withDefaultPasswordEncoder()
//                        .username("pippo")
//                        .password("foo")
//                        .roles("USER")
//                        .build();
//
//        UserDetails user2 =
//                User.withDefaultPasswordEncoder()
//                        .username("topolino")
//                        .password("foo")
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}
