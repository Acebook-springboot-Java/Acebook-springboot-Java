package com.makersacademy.acebook.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.configuration.SecurityConstants;
import com.makersacademy.acebook.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl(SecurityConstants.SIGN_UP_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {
        try {
            logger.info("---------REQ Body:{}---------",req.toString());
            logger.info("---------ATTEMPT TO JWT AUTHENTICATE---------");
            User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
            logger.info("---------AUTHENTICATE USER:{} PASSWORD:{}---------", creds.getUsername(), creds.getPassword());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(),
                            new ArrayList<>()));

        } catch (IOException e) {
            logger.info("---------NO JWT PROVIDED EXCEPTION--------");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException {
        logger.info("---------JWT AUTHENTICATE SUCCESSFUL--------");

        logger.info("---------Casting principal to user---------");
        org.springframework.security.core.userdetails.User authenticatedSecurityUser = ((org.springframework.security.core.userdetails.User) auth
                .getPrincipal());
        logger.info("---------JWT CREATING TOKEN--------");
        String access_token = JWT.create()
                .withSubject(authenticatedSecurityUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        logger.info("---------JWT created {}---------", access_token);


        logger.info("---------WRITING JWT HEADER RESPONSE---------");
//        Map<String, String> token = new HashMap<>();
//        token.put("token", access_token);

//        Cookie cookie = new Cookie("auth",access_token);
//        cookie.setMaxAge(30 * 60); // expires in 30 minutes
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/login");

        ResponseCookie responseCookie = ResponseCookie.from("auth",access_token)
                .maxAge(30 * 60)
                .path("/") //allow requests send from posts to have cookie set from /login
                .secure(true)
                .httpOnly(true)
                .sameSite("none")
                .build();

        Map<String, Object> responseJson = new HashMap<String, Object>();
        try {
            responseJson.put("timestamp", new Date());
            responseJson.put("status", HttpStatus.OK);
            responseJson.put("isSuccess", Boolean.TRUE);
            responseJson.put("message", "Authentication Successful");
            responseJson.put("data", authenticatedSecurityUser.getUsername());
        } catch (Exception e) {
            responseJson.clear();
            responseJson.put("timestamp", new Date());
            responseJson.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseJson.put("isSuccess", false);
            responseJson.put("message", e.getMessage());
            responseJson.put("data", null);
        }


        logger.info("---------ADDING A COOKIE: {}---------", responseCookie);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());
        new ObjectMapper().writeValue(res.getOutputStream(), responseJson);
    }

}
