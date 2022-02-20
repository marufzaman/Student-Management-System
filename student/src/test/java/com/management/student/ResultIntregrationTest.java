package com.management.student;

import antlr.build.Tool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import com.management.student.profile.resultList.ResultController;
import com.management.student.profile.resultList.ResultEntity;
import com.management.student.profile.resultList.ResultException;
import com.management.student.profile.resultList.ResultRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
 class ResultIntregrationTest {


    @Autowired
    private MockMvc mockMvc;
    private static final ObjectMapper MAPPER = new JsonMapper();
    @Autowired
    ResultRepository resultRepository;

    public String getUrl = "http://localhost:8080/api/result/";

    @Test
     void getAllTest() throws Exception{
//        HttpHeaders httpHeaders =  new HttpHeaders();
//        HttpEntity<String> httpEntity  =  new HttpEntity<String>(null,httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl() ,
//                HttpMethod.GET, httpEntity, String.class);
//        assertNotNull(responseEntity.getBody());
//        MvcResult mvcResult = mockMvc.perform(get(getUrl()))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.ResultEntity").value("Hello World!!!"))
//                .andReturn();
//
//       assertEquals("application/json;charset=UTF-8",
//                mvcResult.getResponse().getContentType());

        //Expected output

        ResultEntity res =  new ResultEntity();
        res.setId(12);
        res.setStatus("fail");
        resultRepository.saveAndFlush(res);
        ResultEntity res1 =  new ResultEntity();
        res1.setId(13);
        res1.setStatus("pass");
        resultRepository.saveAndFlush(res1);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(getUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].status").value("fail"))
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[1].status").value("pass"))
                .andExpect(jsonPath("$.[1].id").value(13))
                .andDo(print());
              System.out.println("Testing ............................................");
//                .andExpect(jsonPath("$.id").value(1))
//                .andReturn().getResponse().getContentAsString();


//        mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .get("/api/department/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(MAPPER.writeValueAsString(department))
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.name").value("IBA"))
//                .andExpect(jsonPath("$.id").isNumber());

    }

   @Test
     void getStudentById() throws Exception{
        ResultEntity res =  new ResultEntity();
        res.setId(23);
        res.setStatus("fail");
        resultRepository.saveAndFlush(res);
   mockMvc.perform(MockMvcRequestBuilders.get(getUrl+"23")
           .contentType(MediaType.APPLICATION_JSON)
   ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }
    @Test
    void addTest()throws Exception{
        ResultEntity res =  new ResultEntity();
        res.setId(12);
        res.setStatus("fail");
        resultRepository.saveAndFlush(res);
        mockMvc.perform(MockMvcRequestBuilders.post(getUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(res))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void deleteTest()throws Exception{
        ResultEntity res =  new ResultEntity();
        res.setId(12);
        res.setStatus("fail");
        resultRepository.saveAndFlush(res);
        mockMvc.perform(MockMvcRequestBuilders.delete(getUrl+12)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateCheck() throws Exception{
        ResultEntity res =  new ResultEntity();
        res.setId(12);
        res.setStatus("fail");
        resultRepository.saveAndFlush(res);

        ResultEntity resNew =  new ResultEntity();
        resNew.setId(12);
        resNew.setStatus("pass");
        mockMvc.perform(MockMvcRequestBuilders.put(getUrl+"12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(resNew))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("pass"))
                .andDo(print());


    }


}

