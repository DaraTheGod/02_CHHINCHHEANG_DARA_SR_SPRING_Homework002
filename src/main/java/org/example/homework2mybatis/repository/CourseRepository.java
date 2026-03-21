package org.example.homework2mybatis.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework2mybatis.model.entity.Course;
import org.example.homework2mybatis.model.request.CourseRequest;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Results(id = "courseMapper", value={
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "org.example.homework2mybatis.repository.InstructorRepository.getInstructorById"))
    })


    @Select("""
        select * from courses limit #{size} offset #{offset};
    """)
    List<Course> getAllCourses(int offset, Integer size);


    @ResultMap("courseMapper")
    @Select("""
        insert into courses values (default, #{request.courseName}, #{request.description}, #{request.instructorId}) returning *;
    """)
    Course saveCourse(@Param("request") CourseRequest request);


    @ResultMap("courseMapper")
    @Select("""
        delete from courses where course_id = #{courseId} returning *;
    """)
    Course deleteCourseById(Long courseId);


    @ResultMap("courseMapper")
    @Select("""
        select * from courses where course_id = #{courseId};
    """)
    Course getCourseById(Long courseId);


    @ResultMap("courseMapper")
    @Select("""
        update courses set course_name = #{request.courseName}, description = #{request.description}, instructor_id = #{request.instructorId} where course_id = #{courseId} returning *;
    """)
    Course updateCourseById(Long courseId, @Param("request") CourseRequest request);


//    for student
    @ResultMap("courseMapper")
    @Select("""
        select * from courses c join student_course sc on c.course_id = sc.course_id where sc.student_id = #{studentId};
    """)
    List<Course> getCoursesByStudentId(Long studentId);
}
