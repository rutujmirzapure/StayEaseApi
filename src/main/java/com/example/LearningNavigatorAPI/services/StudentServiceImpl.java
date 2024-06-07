package com.example.LearningNavigatorAPI.services;

import com.example.LearningNavigatorAPI.exceptions.StudentNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import com.example.LearningNavigatorAPI.repositories.LmsStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements LmsStudentService {

    private final LmsStudentRepository studentRepository;

    public StudentServiceImpl(LmsStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<LmsStudent> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<LmsStudent> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public LmsStudent createStudent(LmsStudent student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<LmsStudent> updateStudent(Long studentId, LmsStudent studentDetails) {
        return studentRepository.findById(studentId)
                .map(existingStudent -> {
                    existingStudent.setName(studentDetails.getName());
                    // Update other fields as needed
                    return studentRepository.save(existingStudent);
                });
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}