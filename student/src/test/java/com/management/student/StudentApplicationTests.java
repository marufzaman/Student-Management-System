package com.management.student;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
