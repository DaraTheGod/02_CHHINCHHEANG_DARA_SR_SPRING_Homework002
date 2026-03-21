package org.example.homework2mybatis.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.homework2mybatis.model.entity.Course;
import org.example.homework2mybatis.model.request.CourseRequest;
import org.example.homework2mybatis.model.response.ApiResponse;
import org.example.homework2mybatis.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Get all courses"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Course> courseList = courseService.getAllCourses(page, size);
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Courses fetched successfully")
                .payload(courseList)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Create a new course"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> saveCourse(@RequestBody CourseRequest request) {
        Course course = courseService.saveCourse(request);
        if (course == null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No instructors found with the given ID")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Course created successfully")
                .payload(course)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Delete course by ID"
    )
    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseById(@PathVariable("course-id") Long courseId) {
        Course course = courseService.deleteCourseById(courseId);
        if (course == null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No courses found with the given ID")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Course deleted successfully")
                .payload(null)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Get course by ID"
    )
    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No courses found with the given ID")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Course fetched successfully")
                .payload(course)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Update course by ID"
    )
    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable("course-id") Long courseId, @RequestBody CourseRequest request) {
        Course course = courseService.updateCourseById(courseId, request);
        Course ifExistedCourse = courseService.getCourseById(courseId);
        if  (ifExistedCourse != null) {
            if (course == null) {
                ApiResponse<Course> response = ApiResponse.<Course>builder()
                        .success(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("No instructors found with the given ID")
                        .payload(null)
                        .timestamp(Instant.now())
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            ApiResponse<Course> response = ApiResponse.<Course>builder()
                    .success(true)
                    .status(HttpStatus.OK.value())
                    .message("Course updated successfully")
                    .payload(course)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .message("No courses found with the given ID")
                .payload(null)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
