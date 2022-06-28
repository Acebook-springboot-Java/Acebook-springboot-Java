package com.makersacademy.acebook.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.makersacademy.acebook.configuration.SecurityConstants;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        logger.info("-----------Header:{}", header);

        Cookie[] cookie = req.getCookies();
        logger.info("-----------Cookie:{}", cookie);

        if (cookie == null) {
            logger.info("-----------Cookie is empty------------");
            chain.doFilter(req,res);
            return;
        }

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            logger.info("-----------Header is empty------------");
            logger.info("-----------Header start with:{}------------",header);
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        // if we need role based authorization, use securitycontextholder to set it
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // read the JWT from the Authorization header
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(SecurityConstants.HEADER_STRING);
//        logger.info("-----------Header Constant:{}", token);
        Cookie[] cookies = request.getCookies();
        String cookieContents = Arrays.stream(cookies).filter(c-> "auth".equals(c.getName())).findAny().map(c->c.getValue()).orElse(null);


        if (cookieContents != null) {
            // parse token
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                    .build()
                    .verify(cookieContents)
                    .getSubject();

            if (user != null) {
                // new arraylist means authorities
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

            return null;
        }

        return null;
    }
}