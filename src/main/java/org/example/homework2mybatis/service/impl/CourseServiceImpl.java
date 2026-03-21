package org.example.homework2mybatis.service.impl;

import org.example.homework2mybatis.model.entity.Course;
import org.example.homework2mybatis.model.entity.Instructor;
import org.example.homework2mybatis.model.request.CourseRequest;
import org.example.homework2mybatis.repository.CourseRepository;
import org.example.homework2mybatis.service.CourseService;
import org.example.homework2mybatis.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    public CourseServiceImpl(CourseRepository courseRepository, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return courseRepository.getAllCourses(offset, size);
    }

    @Override
    public Course saveCourse(CourseRequest request) {
        Instructor instructorExists = instructorService.getInstructorById(request.getInstructorId());
        if (instructorExists == null) {
            return null;
        }
        return courseRepository.saveCourse(request);
    }

    @Override
    public Course deleteCourseById(Long courseId) {
        return courseRepository.deleteCourseById(courseId);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public Course updateCourseById(Long courseId, CourseRequest request) {
        Instructor instructorExists = instructorService.getInstructorById(request.getInstructorId());
        if (instructorExists == null) {
            return null;
        }
        return courseRepository.updateCourseById(courseId, request);
    }
}
