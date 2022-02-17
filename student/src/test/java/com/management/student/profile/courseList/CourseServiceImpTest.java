package com.management.student.profile.courseList;

import com.management.student.profile.studentList.StudentService;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
       assertThat(courseEntityList).isEqualTo(responseCourseEntityList);

    }



    @Test
    void createCourse() {
        //when
        CourseEntity courseEntity = new CourseEntity("Spring Boot)");
        courseServiceImp.createCourse(courseEntity);


        //then
        ArgumentCaptor <CourseEntity> courseEntityArgumentCaptor =
                ArgumentCaptor.forClass(CourseEntity.class);
        verify(courseRepository).save(courseEntityArgumentCaptor.capture());

        CourseEntity courseEntityCapture = courseEntityArgumentCaptor.getValue();
        assertThat(courseEntityCapture).isEqualTo(courseEntity);
    }

    @Test
    void getCourseById() throws Exception {




        CourseEntity courseEntity = new CourseEntity("CSE");

        //when

        Mockito.when(courseRepository.findById(courseEntity.getCourseId())).thenReturn(Optional.of(courseEntity));
        CourseEntity outCourseEntity = courseServiceImp.getCourseById(courseEntity.getCourseId());

        //assertThat(courseEntity,outCourseEntity);
        //verify(courseRepository).findById(1L).get();
        assertThat(courseEntity).isEqualTo(outCourseEntity);

    }

    @Test
    void updateCourse() throws Exception {
        //when
       /* CourseEntity inputCourseEntity = new CourseEntity(1L,"Spring Boot");
        CourseEntity outputCourseEntity = new CourseEntity(1L,"Boot");
        CourseEntity outputEntity = new CourseEntity();
        courseServiceImp.createCourse(inputCourseEntity);

git


        //Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(inputCourseEntity));
        courseServiceImp.createCourse(inputCourseEntity);
        Mockito.when(courseServiceImp.updateCourse(inputCourseEntity)).thenReturn(outputEntity);
       // outputEntity.setCourseName(courseServiceImp.updateCourse(outputCourseEntity).getCourseName());
        //assertThat(courseEntity).isEqualTo(expectedCourseEntity);
        assertEquals(inputCourseEntity.getCourseName(),outputEntity.getCourseName());
*/
         //assertEquals(courseEntity.getCourseName(),expectedCourseEntity.getCourseName());
       /*Assertions.assertThrows(Exception.class, ()->{
            courseServiceImp.updateCourse(courseEntity);
        });*/
        //then

    }

    @Test
    void deleteCourse() {
        //when
        CourseEntity courseEntity = new CourseEntity(1L,"Spring Boot)");



        Mockito.when(courseRepository.findById(courseEntity.getCourseId())).thenReturn(Optional.of(courseEntity));
        courseServiceImp.deleteCourse(courseEntity.getCourseId());
        //then

        verify(courseRepository).deleteById(courseEntity.getCourseId());
    }
}