package com.management.student.profile.resultList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("/api/result")
    public List<ResultEntity> ViewHomePage(Model model) {
        //responsible for handle the  request to fetch all table data
        return resultService.getAllResult();
    }
}
