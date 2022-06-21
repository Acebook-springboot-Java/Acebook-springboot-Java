package com.makersacademy.acebook.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuthorityTest {
    @Test
    public void hasAuthorityProperties() {
        Authority auth = new Authority("testUser1", "ROLE_USER");
        assertEquals("testUser1", auth.getUsername());
        assertEquals("ROLE_USER", auth.getAuthority());
    }
}
