package com.management.student.profile.studentList;

import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentProfileRepository studentProfileRepository;
    @InjectMocks
    private StudentService studentService;


    @Test
    void getStudentProfiles() throws Exception {
        List<StudentProfile> mockLists = new ArrayList<>();
        StudentProfile s1 = new StudentProfile(1L, "ABC", "Other");
        StudentProfile s2 = new StudentProfile(2L, "DEF", "Other");

        mockLists.add(s1); mockLists.add(s2);

		Mockito.when(studentProfileRepository.findAll()).thenReturn(mockLists);

		List<StudentProfile> studentProfiles = studentService.getStudentProfiles();

		assertEquals(mockLists, studentProfiles);
    }

    @Test
    void getStudent() throws Exception {
        Long studentID = 1L;
        StudentProfile input = new StudentProfile(studentID, "ABC", "Others");

		Mockito.when(studentProfileRepository.existsById(studentID)).thenReturn(true);
        Mockito.when(studentProfileRepository.findById(studentID)).thenReturn(Optional.of(input));
		Optional<StudentProfile> output = studentService.getStudent(studentID);

		assertEquals(input, output.get());

        Long finalStudentID = ++studentID;
        assertThrows(Exception.class, ()-> {
			studentService.getStudent(finalStudentID);
		});
    }

    @Test
    void addNewStudent() throws Exception{
        StudentProfile studentProfile = new StudentProfile("ABC", "Other");
        studentProfileRepository.save(studentProfile);

        ArgumentCaptor <StudentProfile> studentProfileArgumentCaptor
                = ArgumentCaptor.forClass(StudentProfile.class);
        verify(studentProfileRepository).save(studentProfileArgumentCaptor.capture());

        StudentProfile studentProfileCapture = studentProfileArgumentCaptor.getValue();
        assertThat(studentProfileCapture).isEqualTo(studentProfile);
    }

    @Test
    void deleteStudentProfile() throws Exception{
        Long studentID = 2L;
        studentService.deleteStudentProfile(studentID);
        verify(studentProfileRepository).deleteById(studentID);
    }

    @Test
    void editStudentProfile() throws Exception{
        StudentProfile studentProfile = new StudentProfile(1L, "ABC", "Other");
        studentProfileRepository.save(studentProfile);

        ArgumentCaptor <StudentProfile> studentProfileArgumentCaptor
                = ArgumentCaptor.forClass(StudentProfile.class);
        verify(studentProfileRepository).save(studentProfileArgumentCaptor.capture());

        StudentProfile studentProfileCapture = studentProfileArgumentCaptor.getValue();
        assertThat(studentProfileCapture).isEqualTo(studentProfile);
    }
}