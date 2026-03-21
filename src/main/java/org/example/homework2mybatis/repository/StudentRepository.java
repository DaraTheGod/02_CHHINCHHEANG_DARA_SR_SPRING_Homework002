package org.example.homework2mybatis.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework2mybatis.model.entity.Student;
import org.example.homework2mybatis.model.request.StudentRequest;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Results(id = "studentMapper", value={
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "org.example.homework2mybatis.repository.CourseRepository.getCoursesByStudentId"))
    })


    @Select("""
        select * from students limit #{size} offset #{offset};
    """)
    List<Student> getAllStudents(int offset, Integer size);


    @ResultMap("studentMapper")
    @Select("""
        insert into students values(default, #{request.studentName}, #{request.email}, #{request.phoneNumber}) returning *;
    
    """)
    Student saveStudent(@Param("request") StudentRequest request);


    @ResultMap("studentMapper")
    @Select("""
        delete from students where student_id = #{studentId} returning *;
    """)
    Student deleteStudentById(Long studentId);


    @ResultMap("studentMapper")
    @Select("""
        select * from students where student_id = #{studentId};
    """)
    Student getStudentById(Long studentId);


    @ResultMap("studentMapper")
    @Select("""
        update students set student_name = #{request.studentName}, email = #{request.email}, phone_number = #{request.phoneNumber} where student_id = #{studentId} returning *;
    """)
    Student updateStudentById(Long studentId, @Param("request") StudentRequest request);


//    for student_course
    @ResultMap("studentMapper")
    @Select("""
        insert into student_course values(#{studentId}, #{courseId});
    """)
    void saveStudentCourse(Long studentId, Long courseId);

    @ResultMap("studentMapper")
    @Select("""
        delete from student_course where student_id = #{studentId};
    """)
    void deleteStudentCourseByStudentId(Long studentId);
}
