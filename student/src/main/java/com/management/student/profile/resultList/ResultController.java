package com.management.student.profile.resultList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

@RestController
public class ResultController {

    @Autowired
    public ResultRepository results;

    @GetMapping("/api/result")
    public List<ResultEntity> ViewResult() {
        return (List<ResultEntity>) results.findAll();
    }

    @PostMapping("/api/result")
    public ResultEntity saveResult(@RequestBody ResultEntity result) {
        results.save(result);
        return result;
    }
  @DeleteMapping("/api/result/{id}")
  public List<ResultEntity> DeleteResult(@PathVariable Integer id) {
        results.deleteById(id);
      return (List<ResultEntity>) results.findAll();
  }
    @PutMapping("/api/result/{id}")
    public String UpdateResult(@PathVariable Integer id,@RequestBody ResultEntity result) {

        Optional<ResultEntity> optional = results.findById(id);

       if(optional.isPresent()){

           results.save(result);

           return "success" ;
       }
       else {
           return "fail" ;
       }



    }
//    @PostMapping("/api/result")
//    public List<ResultEntity> saveResult( ResultEntity resultEntity) {
//        System.out.println("hy--------------------------------------------------------");
//        //responsible for handle the  request to save the given data to the  table
//        resultService.saveStudent(resultEntity);
//
//        return resultService.getAllResult();
//
//    }
//
//    @GetMapping("/api/hello")
//    public String method(){
//        return "hello";
//    }
}
