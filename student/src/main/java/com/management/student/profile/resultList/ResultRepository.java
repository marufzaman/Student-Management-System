package com.management.student.profile.resultList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableAutoConfiguration
@Repository
public interface ResultRepository extends CrudRepository<ResultEntity,Integer> {

}
