package com.makersacademy.acebook.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        ResponseCookie responseCookie = ResponseCookie.from("auth","")
                .maxAge(0)
                .path("/") //allow requests send from posts to have cookie set from /login
                .secure(true)
                .httpOnly(true)
                .sameSite("none")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());
    }
}
