package com.management.student.profile.courseList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CourseServiceImpTest {

    @Mock
    private CourseRepository courseRepository;


    @InjectMocks
    private CourseServiceImp courseServiceImp;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        courseServiceImp = new CourseServiceImp(courseRepository);

    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    void getAllCourses() {
        //when
        List<CourseEntity> courseEntityList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity("Spring Boot)");
        CourseEntity courseEntity1 = new CourseEntity("Spring )");

        courseEntityList.add(courseEntity);
        courseEntityList.add(courseEntity1);
        Mockito.when(courseRepository.findAll()).thenReturn(courseEntityList);

        List<CourseEntity> responseCourseEntityList = courseServiceImp.getAllCourses();
        //then
        assertThat(courseEntityList.size()).isEqualTo(responseCourseEntityList.size());

    }



    @Test
    void createCourse() {
        //when
        CourseEntity courseEntity = new CourseEntity("Spring Boot)");
        Mockito.when(courseRepository.save(courseEntity)).thenReturn(courseEntity);
        CourseEntity outputCourseEntity = courseServiceImp.createCourse(courseEntity);
        assertEquals(courseEntity,outputCourseEntity);
    }

    @Test
    void getCourseById() throws Exception {

        CourseEntity courseEntity = new CourseEntity("CSE");
        Mockito.when(courseRepository.findById(courseEntity.getCourseId())).thenReturn(Optional.of(courseEntity));
        CourseEntity outCourseEntity = courseServiceImp.getCourseById(courseEntity.getCourseId());
        System.out.println(courseEntity.getCourseId());
        assertEquals(courseEntity.getCourseId(),outCourseEntity.getCourseId());

    }

    @Test
    void updateCourse() throws Exception {
        //when
        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot)");
        Mockito.when(courseRepository.findById(courseEntity.getCourseId())).thenReturn(Optional.of(courseEntity));
        Mockito.when(courseRepository.save(courseEntity)).thenReturn(courseEntity);
        CourseEntity outputCourseEntity = courseServiceImp.updateCourse(courseEntity);
        assertEquals(courseEntity,outputCourseEntity);

    }

    @Test
    void deleteCourse() {
        //when
        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot)");
        courseServiceImp.deleteCourse(courseEntity.getCourseId());
        verify(courseRepository,times(1)).deleteById(courseEntity.getCourseId());
    }
}