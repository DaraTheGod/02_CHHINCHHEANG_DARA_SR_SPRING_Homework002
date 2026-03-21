package org.example.homework2mybatis.service;

import org.example.homework2mybatis.model.entity.Course;
import org.example.homework2mybatis.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer page, Integer size);

    Course saveCourse(CourseRequest request);

    Course deleteCourseById(Long courseId);

    Course getCourseById(Long courseId);

    Course updateCourseById(Long courseId, CourseRequest request);
}
