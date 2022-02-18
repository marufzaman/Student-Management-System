package com.management.student.profile.departmentList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentControllerTest {


    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentRepository departmentRepositoryOriginal;

    private String baseUrl = "http://localhost:8080/api/department";

    @InjectMocks
    private DepartmentServices departmentServices;

    @Test
    void getDepartmentById() throws Exception {
        DepartmentEntity input = new DepartmentEntity();
        input.setId(1);
        input.setName("CSE");

        Mockito.when(departmentRepository.findById(1)).thenReturn(Optional.of(input));
        DepartmentEntity output = departmentServices.getDepartmentById(1);

        assertEquals(input, output);

        assertThrows(Exception.class, ()-> {
            departmentServices.getDepartmentById(2);
        });

    }

    @Test
    void getDepartmentsTest(){
        DepartmentEntity department1 = new DepartmentEntity();
        DepartmentEntity department2 = new DepartmentEntity();
        List<DepartmentEntity> departments = new ArrayList<>();

        departments.add(department1);
        departments.add(department2);

        Mockito.when(departmentRepository.findAll()).thenReturn(departments);

        List<DepartmentEntity> allDepartments = departmentServices.getDepartments();

        assertEquals(departments,allDepartments);
    }


    @Test
    void createDepartmentTest(){
        DepartmentEntity input = new DepartmentEntity();
        input.setId(1);
        input.setName("CSE");

        DepartmentEntity createdDepartment = new DepartmentEntity();
        createdDepartment.setId(1);
        createdDepartment.setName("CSE");

        Mockito.when(departmentRepository.save(input)).thenReturn(createdDepartment);
        DepartmentEntity output = departmentServices.createDepartment(input);

        assertEquals(input,output);

    }

    @Test
    void updateDepartmentTest() throws Exception {
        DepartmentEntity department = new DepartmentEntity();
        department.setId(1);
        department.setName("CSE");

        Mockito.when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        DepartmentEntity updateBody = new DepartmentEntity();
        updateBody.setId(1);
        updateBody.setName("EEE");

        DepartmentEntity updatedDepartment = departmentServices.updateDepartment(department.id, updateBody);

        assertEquals(updateBody.getName(),updatedDepartment.getName());

        assertThrows(Exception.class, ()->{
            departmentServices.updateDepartment(2,updateBody);
        });

    }
}