package org.example.homework2mybatis.service.impl;

import org.example.homework2mybatis.model.entity.Course;
import org.example.homework2mybatis.model.entity.Student;
import org.example.homework2mybatis.model.request.StudentRequest;
import org.example.homework2mybatis.repository.StudentRepository;
import org.example.homework2mybatis.service.CourseService;
import org.example.homework2mybatis.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;
    public StudentServiceImpl(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return studentRepository.getAllStudents(offset, size);
    }

    @Override
    public Student saveStudent(StudentRequest request) {
        for (Long courseId : request.getCourseId()) {
            Course course = courseService.getCourseById(courseId);
            if (course == null) {
                return null;
            }
        }
        Student student = studentRepository.saveStudent(request);
        for (Long courseId : request.getCourseId()) {
            studentRepository.saveStudentCourse(student.getStudentId(), courseId);
        }
        return studentRepository.getStudentById(student.getStudentId());
    }

    @Override
    public Student deleteStudentById(Long studentId) {
        return studentRepository.deleteStudentById(studentId);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Student updateStudentById(Long studentId, StudentRequest request) {
        for (Long courseId : request.getCourseId()) {
            Course course = courseService.getCourseById(courseId);
            if (course == null) {
                return null;
            }
        }
        Student student = studentRepository.updateStudentById(studentId, request);
        studentRepository.deleteStudentCourseByStudentId(studentId);
        for (Long courseId : request.getCourseId()) {
            studentRepository.saveStudentCourse(student.getStudentId(), courseId);
        }
        return studentRepository.getStudentById(student.getStudentId());
    }
}
