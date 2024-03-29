package com.management.student.profile.departmentList;
//import org.junit.Test;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DepartmentApiTest {

    private final String baseUrl = "/api/department/";
    private static final ObjectMapper MAPPER = new JsonMapper();

    @Autowired
    private MockMvc mockMvc;

    private DepartmentServices departmentServices;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    void getAllDepartments() throws Exception {
        DepartmentEntity department1 = new DepartmentEntity(1,"CSE");
        DepartmentEntity department2 = new DepartmentEntity(2,"EEE");

        departmentRepository.saveAndFlush(department1);
        departmentRepository.saveAndFlush(department2);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(baseUrl)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("CSE"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].name").value("EEE"))
                .andReturn();
    }

    @Test
    void getDepartmentById() throws Exception {

        DepartmentEntity department = new DepartmentEntity();
        department.setId(1);
        department.setName("BBA");
        departmentRepository.saveAndFlush(department);


        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void creteDepartment() throws Exception {
        DepartmentEntity department = new DepartmentEntity(20, "CSE");

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(MAPPER.writeValueAsString(department))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value("CSE"))
                .andReturn();
    }

    @Test
    void updateDepartments() throws Exception {

        //Previous
        DepartmentEntity department = new DepartmentEntity(20, "CSE");

        departmentRepository.saveAndFlush(department);

        //Updated
        DepartmentEntity updatedDepartment = new DepartmentEntity(20, "EEE");

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl+"20")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(updatedDepartment))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(updatedDepartment.getName()))
                .andReturn();

    }


    @Test
    void deleteDepartment() throws Exception {
        //Previous
        DepartmentEntity department = new DepartmentEntity(25, "EMBA");
        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl+"{id}",department.getId())

        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }


}

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
