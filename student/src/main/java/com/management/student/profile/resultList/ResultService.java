//package com.management.student.profile.resultList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class ResultService {
//
//    @Autowired
//    private ResultRepository resultRepository;
//
//    private List<ResultEntity> showresult = new ArrayList<>();
//
//    public ResultService(){
//        ResultEntity resultEntity = new ResultEntity(1,1,"Pass");
//        ResultEntity resultEntity2 = new ResultEntity(2,2,"Fail");
//        showresult.add(resultEntity);
//        showresult.add(resultEntity2);
//
//    }
//
//    public List<ResultEntity> getAllResult() {
//        return (List<ResultEntity>) resultRepository.findAll();
// }
//
//    public void saveStudent(ResultEntity resultEntity) {
//        resultRepository.save(resultEntity);
//
//    }
//
//}
