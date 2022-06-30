package com.makersacademy.acebook.controller;

import  static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
// import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;

    @Test
    public void initialGetIsEmpty() throws URISyntaxException 
    {
        // Same as user controller test!
        String baseUrl = "http://localhost:"+randomServerPort+"/users";
        URI uri = new URI(baseUrl);
        User userBob = new User("Bob", "bobPass123"); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(userBob);
            HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
            ResponseEntity<String> result = this.restTemplate.postForEntity(
              uri, request, String.class
              );
            // Post login request at login URL
            String loginUrl = "http://localhost:"+randomServerPort+"/login";
            URI loginUri = new URI(loginUrl);
            ResponseEntity<String> loginResult = this.restTemplate.postForEntity(
              loginUri, request, String.class
              );
            // Verify login request succeed
            Assert.assertEquals(200, loginResult.getStatusCodeValue());
            Assert.assertThat(loginResult.toString(), containsString("token"));
            // Extract token from Response string
            String resultString = loginResult.toString();
            String tokenString = "auth=" + resultString.substring(15,179);
            System.out.println(tokenString);
            // Set headers for posts Post request
            HttpHeaders postHeaders = new HttpHeaders();
            postHeaders.set("Accept", "*/*");
            postHeaders.set("Accept-Encoding", "gzip, deflate, br");
            postHeaders.set("Authorization", "Bearer");
            postHeaders.set("Connection", "keep-alive");
            postHeaders.set("Content-Length", "0");
            postHeaders.setContentType(MediaType.APPLICATION_JSON);
            postHeaders.set("Cookie", tokenString);
            //postHeaders.set("Credentials", "include");
            //postHeaders.set("Host", "localhost");
            System.out.println(postHeaders);
            String postUrl = "http://localhost:"+randomServerPort+"/posts";
            URI postUri = new URI(postUrl);
            //ResponseEntity<String> postResult = this.restTemplate.getForObject(postUri, headers, String.class);
            
            HttpEntity<Void> requestEntity = new HttpEntity<>(postHeaders);
            ResponseEntity<String> postResult = restTemplate.exchange(
              postUri, HttpMethod.GET, requestEntity, String.class
              );
            System.out.println(postResult);/*
            Assert.assertThat(postResult.toString(), containsString("200"));
            Assert.assertThat(postResult.toString(), containsString("true"));
            Assert.assertThat(postResult.toString(), containsString("username created"));
            System.out.println(postResult);*/
          } catch (IOException e) {
            e.printStackTrace();
        }
    }
  // next test here
}