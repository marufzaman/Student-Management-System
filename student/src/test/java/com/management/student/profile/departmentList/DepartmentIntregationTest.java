//package com.management.student.profile.departmentList;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DepartmentIntregationTest {
//
//    private final String baseUrl = "http://localhost:8080/api/department";
//    private static final ObjectMapper MAPPER = new JsonMapper();
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private DepartmentServices departmentServices;
//    private DepartmentRepository departmentRepository;
//
//
//    @Test
//    void getAllDepartments() {
//
//    }
//
//    @Test
//    public void getDepartmentById() throws Exception {
//
//        //Expected output
//        DepartmentEntity department = new DepartmentEntity(1, "IBA");
//
//        System.out.println("Testing");
//
//        mockMvc.perform(
//                MockMvcRequestBuilders
//                .get("http://localhost:8080/api/department")
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.name").value("IBA"));
//
//
////        mockMvc.perform(
////                        MockMvcRequestBuilders
////                                .get("/api/department/1")
////                                .contentType(MediaType.APPLICATION_JSON)
////                                .content(MAPPER.writeValueAsString(department))
////                                .accept(MediaType.APPLICATION_JSON)
////                )
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(jsonPath("$.name").value("IBA"))
////                .andExpect(jsonPath("$.id").isNumber());
//
//    }
//
//    @Test
//    void creteDepartment() {
//
//    }
//
//    @Test
//    void updateDepartments() {
//
//    }
//
//
//}
