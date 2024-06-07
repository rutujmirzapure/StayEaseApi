package com.example.LearningNavigatorAPI.services;

import com.example.LearningNavigatorAPI.exceptions.SubjectNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsSubject;

import java.util.List;
import java.util.Optional;
public interface LmsSubjectService {
	
	List<LmsSubject> getAllSubjects();
    Optional<LmsSubject> getSubjectById(Long subjectId);
    LmsSubject createSubject(LmsSubject subject);
    Optional<LmsSubject> updateSubject(Long subjectId, LmsSubject subjectDetails);
    void deleteSubject(Long subjectId);
    void enrollStudent(Long subjectId, Long studentId) throws SubjectNotFoundException;

}
