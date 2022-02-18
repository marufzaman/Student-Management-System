package com.management.student.profile.studentList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)


class StudentServiceTest {

    @Mock
    private StudentProfileRepository studentProfileRepository;

    @InjectMocks
    private StudentService studentService;


    @Test
    void getStudentProfiles(){
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
    void addNewStudent(){
        StudentProfile mock = new StudentProfile("ABC", "Other");
        Mockito.when(studentProfileRepository.save(mock)).thenReturn(mock);
        StudentProfile expected = studentService.addNewStudent(mock);
        assertEquals(expected, mock);
    }

    @Test
    void deleteStudentProfile() throws Exception{
        Long studentID = 1L;
        StudentProfile input = new StudentProfile(studentID, "ABC", "Others");

        Mockito.when(studentProfileRepository.existsById(input.getId())).thenReturn(true);
        studentProfileRepository.deleteById(input.getId());

        Long finalStudentID = ++studentID;
        assertThrows(Exception.class, ()-> {
            studentService.deleteStudentProfile(finalStudentID);
        });
    }

    @Test
    void editStudentProfile() throws Exception{
        Long studentID = 1L;
        StudentProfile studentProfile = new StudentProfile(studentID, "ABC", "Other");

        String studentName = "ABCDEF";
        String studentGender = "Other";

        Mockito.when(studentProfileRepository.findById(studentID)).thenReturn(Optional.of(studentProfile));

        StudentProfile updateStudentProfile = studentService.editStudentProfile(studentID, studentName, studentGender);

        assertEquals(updateStudentProfile.getName(), studentName);

        studentGender = "Male";
        updateStudentProfile = studentService.editStudentProfile(studentID, studentName, studentGender);

        assertEquals(updateStudentProfile.getGender(), studentGender);

        Long finalStudentID = ++studentID;
        assertThrows(Exception.class, ()->{
            studentService.editStudentProfile(finalStudentID, studentName, studentName);
        });
    }
}