package com.management.student;

import com.management.student.profile.resultList.ResultController;
import com.management.student.profile.resultList.ResultEntity;
import com.management.student.profile.resultList.ResultException;
import com.management.student.profile.resultList.ResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ResultTests {



	@Mock
	private ResultRepository resultRepository;
	@InjectMocks
	private ResultController resultController;


	@Test
	public void getByIdTest() throws ResultException {

		ResultEntity result =  new ResultEntity();
		result.setId(1);
		result.setStatus("pass");

		Mockito.when(resultRepository.findById(1)).thenReturn(Optional.of(result));

		Optional<ResultEntity> resultnew = resultController.getStudentById(1);
//		Optional<ResultEntity> resultnew2 = resultController.getStudentById(2);
		assertEquals(result,resultnew.get());


		Assertions.assertThrows(ResultException.class, () -> {
			resultController.getStudentById(2);
		});



	}

	@Test
	public void getAll(){

		List<ResultEntity> list  =  new ArrayList<>();
		ResultEntity result =  new ResultEntity();
		result.setId(1);
		result.setStatus("pass");
		ResultEntity result1 =  new ResultEntity();
		result1.setId(2);
		result1.setStatus("fail");
		ResultEntity result3 =  new ResultEntity();
		result3.setId(3);
		result1.setStatus("pass");
		list.add(result);
		list.add(result1);

		list.add(result3);

		Mockito.when(resultRepository.findAll()).thenReturn(list);

		List<ResultEntity> newlist =  resultController.ViewResult();
		assertEquals(list,newlist);

	}

	@Test
	public void updateCheck() throws ResultException{
		ResultEntity  beforeUpdate =  new ResultEntity();
		beforeUpdate.setId(1);
		beforeUpdate.setStatus("pass");



		Mockito.when(resultRepository.findById(1)).thenReturn(Optional.of(beforeUpdate));

		ResultEntity  update =  new ResultEntity();
		update.setId(1);
		update.setStatus("fail");

		ResultEntity resultEntity = resultController.UpdateResult(1, update);
		assertEquals(resultEntity.getStatus(), update.getStatus());
		Assertions.assertThrows(ResultException.class, () -> {
			resultController.getStudentById(2);
		});

	}

	@Test
	public void deleteExceptionCheck() throws  ResultException{

//		ResultEntity  delete1 =  new ResultEntity();
//		delete1.setId(1);
//		delete1.setStatus("pass");
//
//		ResultEntity  delete2 =  new ResultEntity();
//		delete2.setId(2);
//		delete2.setStatus("fail");
//		List<ResultEntity> list  =  new ArrayList<>();
//		list.add(delete1);
//		list.add(delete2);
//		Mockito.when(resultRepository.delete(delete1)).thenReturn(1);
//
//		int i = resultController.DeleteResult(1);
//		assertEquals(i,1);

		Assertions.assertThrows(ResultException.class, () -> {
			resultController.DeleteResult(3);
		});



	}

	@Test
	public void addTest()throws ResultException{
		ResultEntity  Add =  new ResultEntity();
		Add.setId(1);
		Add.setStatus("pass");
		Mockito.when(resultRepository.save(Add)).thenReturn(Add);

		String rightstr = "ok add done";
		String getStr = resultController.saveResult(Add);

		assertEquals(rightstr,getStr);

		ResultEntity  nullAdd =  new ResultEntity();
		nullAdd.setId(2);
		nullAdd.setStatus(null);

		Assertions.assertThrows(ResultException.class, () -> {
			resultController.saveResult(nullAdd);
		});


	}

}
