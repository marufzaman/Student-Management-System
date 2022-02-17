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
    public ResultEntity saveResult (@RequestBody ResultEntity result) throws ResultException{
        if(result.getStatus()==null){
            throw new ResultException(
                    "Status cant be null");
        }
        else{
            results.save(result);
            return result;
        }

    }

    @DeleteMapping("/api/result/{id}")
    public Integer  DeleteResult(@PathVariable Integer id) throws ResultException {
        if (results.findById(id).isPresent()) {
            results.deleteById(id);
            return id;
        } else {
            throw new ResultException(
                    "Could not find student with ID " + id);

        }

    }

    @PutMapping("/api/result/{id}")
    public ResultEntity UpdateResult(@PathVariable Integer id, @RequestBody ResultEntity result) throws ResultException {

        Optional<ResultEntity> optional = results.findById(id);
        ResultEntity temp;
        if (optional.isPresent()) {
            temp = optional.get();
            temp.setStatus(result.getStatus());

            results.save(temp);
            return temp;

        } else {
            throw new ResultException(
                    "Could not find student with ID " + id);
        }

    }

    @GetMapping("/api/result/{id}")
    public Optional<ResultEntity> getStudentById(@PathVariable Integer id) throws ResultException {
        if (results.findById(id).isPresent()) {
            Optional<ResultEntity> optional = results.findById(id);
            return optional;
        } else {
            throw new ResultException(
                    "Could not find student with ID " + id);
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
