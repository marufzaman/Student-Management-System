package com.management.student;

import antlr.build.Tool;
import com.management.student.profile.resultList.ResultController;
import com.management.student.profile.resultList.ResultRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultIntregrationTest {

    @Mock
    private ResultRepository resultRepository;
    @InjectMocks
    private ResultController resultController;
    @Autowired
    public TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private MockMvc mockMvc;

    public String getUrl(){
        return "/api/result";
    }
    @Test
    public void getAllTest() throws Exception {


//        HttpHeaders httpHeaders =  new HttpHeaders();
//        HttpEntity<String> httpEntity  =  new HttpEntity<String>(null,httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl() ,
//                HttpMethod.GET, httpEntity, String.class);
//        assertNotNull(responseEntity.getBody());

        MvcResult mvcResult = mockMvc.perform(get(getUrl()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.ResultEntity").value("Hello World!!!"))
                .andReturn();

       assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());

    }
}
