package com.management.student.profile.studentList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(baseURL)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getStudent() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(baseURL+"/{id}", 1)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void addNewStudentProfile() throws Exception {
        StudentProfile s1 = new StudentProfile("ABC", "Other");
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(baseURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(s1))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value("ABC"))
                .andExpect(jsonPath("$.gender").value("Other"))
                .andReturn();
    }

    @Test
    void deleteStudentProfile() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(baseURL+"/{id}", 1)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void editStudentProfile() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(baseURL+"/{id}?name={name}&gender={gender}", 1, "DEF JFK", "Male"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("DEF JFK"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andReturn();
    }
}