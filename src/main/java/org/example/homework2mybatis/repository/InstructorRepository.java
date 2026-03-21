package org.example.homework2mybatis.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework2mybatis.model.entity.Instructor;
import org.example.homework2mybatis.model.request.InstructorRequest;

import java.util.List;

@Mapper
public interface InstructorRepository {
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })


    @Select("""
        select * from instructors limit #{size} offset #{offset};
    """)
    List<Instructor> getAllInstructors(int offset, Integer size);


    @ResultMap("instructorMapper")
    @Select("""
        insert into instructors values (default, #{request.instructorName}, #{request.email}) returning *;
    """)
    Instructor saveInstructor(@Param("request") InstructorRequest request);


    @ResultMap("instructorMapper")
    @Select("""
        delete from instructors where instructor_id = #{instructorId} returning *;
    """)
    Instructor deleteInstructorById(Long instructorId);


    @ResultMap("instructorMapper")
    @Select("""
        select * from instructors where instructor_id = #{instructorId};
    """)
    Instructor getInstructorById(Long instructorId);


    @ResultMap("instructorMapper")
    @Select("""
        update instructors set instructor_name = #{request.instructorName}, email = #{request.email} where instructor_id = #{instructorId} returning *;
    """)
    Instructor updateInstructorById(Long instructorId, @Param("request") InstructorRequest request);
}
