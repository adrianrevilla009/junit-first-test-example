package com.adrian.testExample;

import com.adrian.testExample.model.Gender;
import com.adrian.testExample.model.Student;
import com.adrian.testExample.service.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // this allows to test repositories in memory
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    private String email;

    @BeforeEach // this runs before each test
    void setUp() {
        email = "adrianrg1996@gmail.com";
    }

    @AfterEach // this runs after each test
    void tearDown() {
        studentRepository.deleteAll();;
    }

    @Test
    void checkStudentExistByEmail() {
        // given
        Student student = new Student("Adrian", email, Gender.MALE);
        studentRepository.save(student);
        // when
        boolean exists = studentRepository.selectExistsEmail(email);
        // then
        assertThat(exists).isTrue();
    }

    @Test
    void checkStudentNotExistByEmail() {
        // given
        Student student = new Student("Adrian", email, Gender.MALE);
        studentRepository.save(student);
        // when
        boolean exists = studentRepository.selectExistsEmail(email + "test");
        // then
        assertThat(exists).isFalse();
    }

}