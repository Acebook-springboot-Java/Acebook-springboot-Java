//package com.makersacademy.acebook.controller;
//
//import com.makersacademy.acebook.Application;
//import com.makersacademy.acebook.repository.AuthoritiesRepository;
//import com.makersacademy.acebook.repository.UserRepository;
//import com.makersacademy.acebook.services.ResponseHandler;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UsersControllerTest {
//
//     @LocalServerPort
//     private int port;
//
//     TestRestTemplate restTemplate = new TestRestTemplate();
//
//     HttpHeaders headers = new HttpHeaders();
//
//     @Test
//     public void usersControllerTests(){
//          HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//          ResponseEntity<String> response = restTemplate.exchange(
//                  createURLWithPort("/students/Student1/courses/Course1"),
//                  HttpMethod.GET, entity, String.class);
//     }
//
//     private String createURLWithPort(String uri) {
//          return "http://localhost:" + port + uri;
//     }
//     @Test
//     public void createUserAPI() throws Exception {
//     this.mockMvc.perform(post("/users"))
//     .content(asJsonString(new User("testuser1", "password")))
//     .contentType(MediaType.APPLICATION_JSON)
//     .accept(MediaType.APPLICATION_JSON)
//     .andExpect(MockMvcResultMatchers.status().isCreated());
//     // .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").exists());
//     }
//
//     public static String asJsonString(final Object obj) {
//         try {
//         return new ObjectMapper().writeValueAsString(obj);
//         } catch (Exception e) {
//         throw new RuntimeException(e);
//         }
//     }
//
//}
