package com.management.student.profile.studentList;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    @Query("SELECT s FROM StudentProfile s " +
            "WHERE s.name = :name and s.gender = :gender")
    Optional<StudentProfile> findStudentProfile(
            @Param("name") String name,
            @Param("gender") String gender
    );

}