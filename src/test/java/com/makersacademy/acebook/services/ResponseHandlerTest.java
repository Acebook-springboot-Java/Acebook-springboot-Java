package com.makersacademy.acebook.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.makersacademy.acebook.model.User;

public class ResponseHandlerTest {

    @Test
    public void shouldHaveASpecificStructure() {
        User testUser = mock(User.class);
        ResponseEntity<?> testResponse = ResponseHandler.generateResponse(HttpStatus.OK, true, "this is a test message",
                testUser);
        assertThat(testResponse).isNotNull();

        // Wed Jun 22 13:04:51 BST 2022
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        Date date = new Date(System.currentTimeMillis());

        assertThat(testResponse.getStatusCode().toString()).isEqualTo("200 OK");
        System.out.println(testResponse.getBody());
        assertThat(testResponse.getBody().toString()).contains(
                "message=this is a test message, timestamp=" + formatter.format(date)
                        + ", status=200, isSuccess=true");
    }
}
