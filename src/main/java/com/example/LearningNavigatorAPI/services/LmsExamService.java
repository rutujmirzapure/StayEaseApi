package com.example.LearningNavigatorAPI.services;

import com.example.LearningNavigatorAPI.exceptions.ExamNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsExam;
import java.util.List;
import java.util.Optional;

public interface LmsExamService {
	Optional<LmsExam> getExamById(Long examId); 
    LmsExam createExam(LmsExam exam);
    Optional<LmsExam> updateExam(Long examId, LmsExam examDetails);
    void deleteExam(Long examId);
    void registerStudentForExam(Long examId, Long studentId) throws ExamNotFoundException;
	 List<LmsExam> getAllExams(); 
	

}
