package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void triangleTest() {
        MultiValueMap<String, Integer> param = new LinkedMultiValueMap<>();
        param.add("a",2);
        param.add("b",3);
        param.add("c",4);
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8080/test/triangle", request , String.class );
        System.out.println(response.getBody());
    }
}
