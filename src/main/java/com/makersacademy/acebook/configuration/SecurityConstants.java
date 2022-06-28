package com.makersacademy.acebook.configuration;

public class SecurityConstants {
    public static final String SECRET = "ThisIs_s3cr3tkey";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Koalas ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";
}