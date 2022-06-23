package com.makersacademy.acebook.config;

public class SecurityConstants {
    public static final String SECRET = "Thisismysecretkey";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Koalas ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/services/controller/user";
}
