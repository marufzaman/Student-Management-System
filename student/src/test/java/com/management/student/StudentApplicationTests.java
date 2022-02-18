package com.management.student;

import com.management.student.profile.departmentList.DepartmentController;
import com.management.student.profile.departmentList.DepartmentEntity;
import com.management.student.profile.departmentList.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentApplicationTests {


	Calculate calculate = new Calculate();
	@Test
	void contextLoads() {

		int numberOne =30;
		int numberTwo = 20;

		int expected =50;

		int result = calculate.add(numberOne,numberTwo);

		assertThat(result).isEqualTo(expected);



	}

	class Calculate{
		public int add(int x,int y){

			return x+y;
		}
	}



}
