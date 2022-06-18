package com.adrian.testExample;

import com.adrian.testExample.exception.BadRequestException;
import com.adrian.testExample.model.Gender;
import com.adrian.testExample.model.Student;
import com.adrian.testExample.service.StudentRepository;
import com.adrian.testExample.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock // enables mock data
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void getAllStudentsTest() {
        // when
        studentService.getAllStudents();
        // then
        verify(studentRepository).findAll();
    }

    @Test
    void addStudentTest() {
        // given
        Student student = new Student("Adrian", "adrianrg1996@gmail.com", Gender.MALE);
        // when
        studentService.addStudent(student);
        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void addStudentTestWhenExceptionThrows() {
        // given
        Student student = new Student("Adrian", "adrianrg1996@gmail.com", Gender.MALE);
        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);

        // then
        assertThatThrownBy(() -> studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

    }

    @Test
    // @Disabled // disables a test
    void deleteStudentTest() { // this is not completed
        // given
        Student student = new Student(1L, "Adrian", "adrianrg1996@gmail.com", Gender.MALE);
        when(studentRepository.existsById(1L)).thenReturn(true);

        // then
        studentService.deleteStudent(student.getId());
    }

}