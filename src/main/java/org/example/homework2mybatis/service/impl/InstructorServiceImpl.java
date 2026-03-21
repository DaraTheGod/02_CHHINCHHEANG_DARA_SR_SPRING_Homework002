package org.example.homework2mybatis.service.impl;

import org.example.homework2mybatis.model.entity.Instructor;
import org.example.homework2mybatis.model.request.InstructorRequest;
import org.example.homework2mybatis.repository.InstructorRepository;
import org.example.homework2mybatis.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return instructorRepository.getAllInstructors(offset, size);
    }

    @Override
    public Instructor saveInstructor(InstructorRequest request) {
        return instructorRepository.saveInstructor(request);
    }

    @Override
    public Instructor deleteInstructorById(Long instructorId) {
        return instructorRepository.deleteInstructorById(instructorId);
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public Instructor updateInstructorById(Long instructorId, InstructorRequest request) {
        return instructorRepository.updateInstructorById(instructorId, request);
    }
}
