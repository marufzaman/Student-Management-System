package com.management.student.profile.studentList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentProfileConfig {
    List<StudentProfile> StudentProfiles = new ArrayList<>();

    @Bean
    CommandLineRunner commandLineRunner(
            StudentProfileRepository profileRepository
    ){
        return args -> {
            StudentProfile studentProfileSample_1 = new StudentProfile(
                    "A. M. Almarufuzzaman",
                    "Male"
            );
            StudentProfiles.add(studentProfileSample_1);

            StudentProfile studentProfileSample_2 = new StudentProfile(
                    "Tausif Islam Abir",
                    "Male"
            );
            StudentProfiles.add(studentProfileSample_2);

            StudentProfile studentProfileSample_3 = new StudentProfile(
                    "Mehedi Hasan",
                    "Male"
            );
            StudentProfiles.add(studentProfileSample_3);

            StudentProfile studentProfileSample_4 = new StudentProfile(
                    "Mohammad Shah Alam",
                    "Male"
            );
            StudentProfiles.add(studentProfileSample_4);

            StudentProfile studentProfileSample_5 = new StudentProfile(
                    "Nusrat Jahan",
                    "Female"
            );
            StudentProfiles.add(studentProfileSample_5);

            profileRepository.saveAll(StudentProfiles);
        };
    }
}