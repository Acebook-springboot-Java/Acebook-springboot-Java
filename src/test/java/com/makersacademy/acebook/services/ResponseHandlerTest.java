package com.makersacademy.acebook.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.makersacademy.acebook.model.User;

@SpringBootTest
public class ResponseHandlerTest {
    // private User testUser = new User("testUser1", "testPass1");
    @Mock
    User testUser;

    @Test
    public void shouldHaveASpecificStructure() {
        System.out.println("hi");
        ResponseEntity<?> actualResponse = ResponseHandler.generateResponse(HttpStatus.OK, true,
                "this is a test message",
                testUser);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", new Date());
        map.put("status", HttpStatus.OK.value());
        map.put("isSuccess", true);
        map.put("message", "this is a test message");
        map.put("data", testUser);
        ResponseEntity<?> expectedResponse = new ResponseEntity<Object>(map, HttpStatus.OK);
        assertThat(actualResponse.getStatusCode().toString()).isEqualTo(expectedResponse.getStatusCode().toString());
        assertThat(actualResponse.getBody().toString()).isEqualTo(actualResponse.getBody().toString());
    }

}
