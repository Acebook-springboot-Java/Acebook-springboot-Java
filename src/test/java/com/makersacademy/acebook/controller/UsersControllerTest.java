package com.makersacademy.acebook.controller;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import  static org.hamcrest.CoreMatchers.containsString;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UsersControllerTest 
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;

    /*
    @Before
    public void init() {
      userRepository.deleteAll();
    }
     */

    @Test
    public void addUserIsSuccess() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/users";
        URI uri = new URI(baseUrl);
        User userBob = new User("Bob", "bobPass123"); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(userBob);
            HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
            ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
            // Verify request succeed
            Assert.assertEquals(201, result.getStatusCodeValue());
            Assert.assertThat(result.toString(), containsString("Bob"));
            Assert.assertThat(result.toString(), containsString("true"));
            Assert.assertThat(result.toString(), containsString("username created"));
            // System.out.println(result);
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUserFails() throws URISyntaxException  
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/users";
        URI uri = new URI(baseUrl);
        User userBob = new User("Bob", "bobPass123"); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(userBob);
            HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
            ResponseEntity<Object> result = this.restTemplate.postForEntity(uri, request, Object.class);
            // Verify request failed
            Assert.assertEquals(409, result.getStatusCodeValue());
            Assert.assertThat(result.toString(), containsString("Bob"));
            Assert.assertThat(result.toString(), containsString("duplicated username"));
            Assert.assertThat(result.toString(), containsString("false"));
            // System.out.println(result);
          } catch (IOException e) {
            e.printStackTrace();
        }
    }
}