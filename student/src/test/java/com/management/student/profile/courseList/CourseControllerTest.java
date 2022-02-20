package com.management.student.profile.courseList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {CourseControllerTest.class})
@AutoConfigureMockMvc
@ContextConfiguration
@ComponentScan(basePackages = "com.management.student")
class CourseControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;


    @InjectMocks
    CourseController courseController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        courseController = new CourseController(courseService);
    }

    @Test
    void getAllCourses() throws Exception {

        List<CourseEntity> courseEntityList = new ArrayList<>();
        courseEntityList.add(new CourseEntity("Spring Boot"));
        courseEntityList.add(new CourseEntity("Spring Boot"));

        when(courseService.getAllCourses()).thenReturn(courseEntityList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/courses"))
                .andExpect(status().isOk())

                .andDo(print());


    }

    @Test
    void addCourse() throws Exception {



       // Mockito.when(courseService.createCourse(courseEntity)).thenReturn(courseEntity);

        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot");

        Mockito.when(courseService.createCourse(courseEntity)).thenReturn(courseEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(courseEntity);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/courses")
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(MockMvcResultMatchers.jsonPath("$.courseId").value(1L))
                .andDo(print());




       /* CourseEntity outputCourseEntity = courseController.addCourse(courseEntity);
        assertEquals(courseEntity,outputCourseEntity);*/
    }

    @Test
    void updateCourse() throws Exception {

        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot");
        Mockito.when(courseService.getCourseById(courseEntity.getCourseId())).thenReturn(courseEntity);
        Mockito.when(courseService.updateCourse(courseEntity)).thenReturn(courseEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(courseEntity);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/courses/{id}",courseEntity.getCourseId())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Spring Boot"))
                .andDo(print());

    }

    @Test
    void deleteCourse() throws Exception {

        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot");
        Mockito.when(courseService.getCourseById(courseEntity.getCourseId())).thenReturn(courseEntity);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/courses/{id}",courseEntity.getCourseId()))
                .andExpect(status().isAccepted())
                .andDo(print());
    }

    @Test
    void getCourseById() throws Exception {


        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot");
        Mockito.when(courseService.getCourseById(courseEntity.getCourseId())).thenReturn(courseEntity);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/courses/{id}",courseEntity.getCourseId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Spring Boot"))
                .andDo(print());
    }
}