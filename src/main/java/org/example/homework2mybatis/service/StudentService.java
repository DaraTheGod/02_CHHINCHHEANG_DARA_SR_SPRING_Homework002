package org.example.homework2mybatis.service;

import org.example.homework2mybatis.model.entity.Student;
import org.example.homework2mybatis.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer page, Integer size);

    Student saveStudent(StudentRequest request);

    Student deleteStudentById(Long studentId);

    Student getStudentById(Long studentId);

    Student updateStudentById(Long studentId, StudentRequest request);
}
