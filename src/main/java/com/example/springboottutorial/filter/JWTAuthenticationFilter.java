package com.example.springboottutorial.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboottutorial.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    final private AuthenticationManager authenticationManager;

    private String JWT_SECRET = "gabriele-93";
    private long JWT_DURATION = 864_000_000;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            UserModel user = new ObjectMapper().readValue(req.getInputStream(), UserModel.class);

            return authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      user.getUsername(),
                      user.getPassword(),
                      new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication authResult) {
        User mUser = ((User) authResult.getPrincipal());
        // creazione token
        String sub = mUser.getUsername();

        String jwtToken =
                JWT.create()
                        .withSubject(sub)
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_DURATION))
                        .withClaim("authorities", StringUtils.join(mUser.getAuthorities(), " "))
                        .sign(Algorithm.HMAC512(JWT_SECRET.getBytes()))
                ;

        res.addHeader("Authorization", "Bearer " + jwtToken);
    }

}
