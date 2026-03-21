package org.example.homework2mybatis.service;

import org.example.homework2mybatis.model.entity.Instructor;
import org.example.homework2mybatis.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer page, Integer size);

    Instructor saveInstructor(InstructorRequest request);

    Instructor deleteInstructorById(Long instructorId);

    Instructor getInstructorById(Long instructorId);

    Instructor updateInstructorById(Long instructorId, InstructorRequest request);
}
