package com.management.student.profile.resultList;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
   List<ResultEntity> resultinput;
    public ResultService(){
        resultinput  =  new ArrayList<>();
        resultinput.add( new ResultEntity(1,1,"Pass"));
        resultinput.add( new ResultEntity(2,2,"Fail"));
        resultinput.add( new ResultEntity(3,3,"Pass"));

    }

    public List<ResultEntity> getAllResult() {

//        return (List<ResultEntity>) resultRepository.findAll();
        return  resultinput;
 }
}
