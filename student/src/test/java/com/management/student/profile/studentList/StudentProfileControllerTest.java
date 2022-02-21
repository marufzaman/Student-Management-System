package com.management.student.profile.studentList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentProfileControllerTest {

    private final String baseURL = "http://localhost:8080/api/v1/students";
    private static final ObjectMapper MAPPER = new JsonMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudentProfiles() throws Exception {
//        StudentProfile s1 = new StudentProfile("ABC", "Other");

        // TESTING TO GET ALL of the LIST; even an empty LIST.
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(baseURL)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getStudent() throws Exception {
        // TESTING for available ID.
        Long getID = 1L;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(baseURL+"/{id}", getID)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        // TESTING for unavailable ID.
        getID = 1000L;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(baseURL+"/{id}", getID)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());

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
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("ABC"))
                .andExpect(jsonPath("$.gender").value("Other"));
    }

    @Test
    void deleteStudentProfile() throws Exception {
        // TESTING for available ID.
        Long deleteID = 1L;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(baseURL+"/{id}", deleteID)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        // TESTING for unavailable ID.
        deleteID = 1000L;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(baseURL+"/{id}", deleteID)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void editStudentProfile() throws Exception {

        StudentProfile newInfoToUpdate = new StudentProfile("DEF JFK", "Male");

       // TESTING for available ID.
        Long editID = 1L;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(baseURL+"/{id}" , editID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(newInfoToUpdate))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(editID))
                .andExpect(jsonPath("$.name").value("DEF JFK"))
                .andExpect(jsonPath("$.gender").value("Male"));

        // TESTING for unavailable ID.
        editID = 1000L;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(baseURL+"/{id}" , editID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(newInfoToUpdate))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}