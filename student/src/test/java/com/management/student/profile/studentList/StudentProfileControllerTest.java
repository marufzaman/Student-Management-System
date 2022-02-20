package com.management.student.profile.studentList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentProfileControllerTest {

    private String baseURL = "http://localhost:8080/api/v1/students";
    private static final ObjectMapper MAPPER = new JsonMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudentProfiles() throws Exception {
//        StudentProfile s1 = new StudentProfile("ABC", "Other");

        try{
            mockMvc.perform(
                MockMvcRequestBuilders
                        .get(baseURL)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getStudent() throws Exception {
        mockMvc.perform(
                    MockMvcRequestBuilders
                            .get(baseURL+"/{id}", 100)
                    )
                    .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void addNewStudentProfile() throws Exception {
        StudentProfile s1 = new StudentProfile("ABC", "Other");
        try{
            mockMvc.perform(
                MockMvcRequestBuilders
                        .post(baseURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(s1))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("ABC"))
                .andExpect(jsonPath("$.gender").value("Other"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteStudentProfile() throws Exception {
        try{
            mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(baseURL+"/{id}", 1)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void editStudentProfile() throws Exception {
        try{
            mockMvc.perform(
                MockMvcRequestBuilders
                        .put(baseURL+"/{id}?name={name}&gender={gender}"
                                , 1, "DEF JFK", "Male"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", "$.name", "$.gender").value(1L))
                .andExpect(jsonPath("$.name").value("DEF JFK"))
                .andExpect(jsonPath("$.gender").value("Male"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}