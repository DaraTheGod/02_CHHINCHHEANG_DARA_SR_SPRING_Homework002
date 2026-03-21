package org.example.homework2mybatis.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.homework2mybatis.model.entity.Student;
import org.example.homework2mybatis.model.request.StudentRequest;
import org.example.homework2mybatis.model.response.ApiResponse;
import org.example.homework2mybatis.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Get all students"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Student> studentList = studentService.getAllStudents(page, size);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Students fetched successfully")
                .payload(studentList)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Create a new student"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest request) {
        Student student = studentService.saveStudent(request);
        if (student == null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No courses found with the given ID")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Student created successfully")
                .payload(student)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete student by ID"
    )
    @DeleteMapping("/{student_id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudentById(@PathVariable("student_id") Long studentId) {
        Student student = studentService.deleteStudentById(studentId);
        if (student == null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No students found with the given ID")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Student deleted successfully")
                .payload(null)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Get student by ID"
    )
    @GetMapping("/{student_id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student_id") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No students found with the given ID")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Student fetched successfully")
                .payload(student)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Update student by ID"
    )
    @PutMapping("/{student_id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable("student_id") Long studentId, @RequestBody StudentRequest request) {
        Student student = studentService.updateStudentById(studentId, request);
        Student ifStudentExisted = studentService.getStudentById(studentId);
        if (ifStudentExisted != null) {
            if (student == null) {
                ApiResponse<Student> response = ApiResponse.<Student>builder()
                        .success(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("No courses found with the given ID")
                        .payload(null)
                        .timestamp(Instant.now())
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .success(true)
                    .status(HttpStatus.OK.value())
                    .message("Student updated successfully")
                    .payload(student)
                    .timestamp(Instant.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .message("No students found with the given ID")
                .payload(null)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
