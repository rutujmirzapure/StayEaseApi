package com.example.LearningNavigatorAPI.services;

import com.example.LearningNavigatorAPI.exceptions.StudentNotFoundException;
import com.example.LearningNavigatorAPI.exceptions.SubjectNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import com.example.LearningNavigatorAPI.models.LmsSubject;
import com.example.LearningNavigatorAPI.repositories.LmsStudentRepository;
import com.example.LearningNavigatorAPI.repositories.LmsSubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements LmsSubjectService {

    private final LmsSubjectRepository subjectRepository;
    private final LmsStudentRepository studentRepository;

    public SubjectServiceImpl(LmsSubjectRepository subjectRepository, LmsStudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<LmsSubject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<LmsSubject> getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId);
    }

    @Override
    public LmsSubject createSubject(LmsSubject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Optional<LmsSubject> updateSubject(Long subjectId, LmsSubject subjectDetails) {
        return subjectRepository.findById(subjectId)
                .map(existingSubject -> {
                    existingSubject.setName(subjectDetails.getName());
                    // Update other fields if needed
                    return subjectRepository.save(existingSubject);
                });
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public void enrollStudent(Long subjectId, Long studentId) 
            throws SubjectNotFoundException, StudentNotFoundException {

        LmsSubject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID: " + subjectId));

        LmsStudent student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));

        // Additional check (optional): Prevent duplicate enrollment
        if (student.getSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student is already enrolled in this subject.");
        }

        subject.getStudents().add(student);
        subjectRepository.save(subject);
    }
}


