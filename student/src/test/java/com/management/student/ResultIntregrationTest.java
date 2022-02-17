package com.management.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultIntregrationTest {

    @Autowired
    public TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    public String getUrl(){
        return "/api/result";
    }
    @Test
    public void getAllTest(){

        HttpHeaders httpHeaders =  new HttpHeaders();
        HttpEntity<String> httpEntity  =  new HttpEntity<String>(null,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl() ,
                HttpMethod.GET, httpEntity, String.class);
        assertNotNull(responseEntity.getBody());

    }
}
