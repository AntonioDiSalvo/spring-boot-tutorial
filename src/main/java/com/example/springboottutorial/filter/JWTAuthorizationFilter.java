package com.example.springboottutorial.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

//  @Value("${token.secret}")
    String tokenSecret = "gabriele-93";

//  @Value("${token.expiry}")
    long tokenExpiry = 864_000_000;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {
        String jwtHeader = req.getHeader("Authorization");

        if (jwtHeader == null) {
            chain.doFilter(req, res);
            return;
        }

        Authentication auth = getAuthentication(jwtHeader);

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(req, res);

    }

    // verifica della firma del JWT
    private Authentication getAuthentication(String jwtHeader) {
            String jwtToken = jwtHeader.replace("Bearer ", "");
            Authentication auth = null;

            if (jwtToken != null) {
                String sub = JWT.require(Algorithm.HMAC512(tokenSecret.getBytes()))
                        .build()
                        .verify(jwtToken)
                        .getSubject();
                if (sub != null) {
                    auth = new UsernamePasswordAuthenticationToken(sub, null, new ArrayList<>());
                }
            }
            return auth;
    }
}
