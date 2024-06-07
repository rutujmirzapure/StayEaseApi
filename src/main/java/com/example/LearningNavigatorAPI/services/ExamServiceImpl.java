package com.example.LearningNavigatorAPI.services;

import com.example.LearningNavigatorAPI.exceptions.ExamNotFoundException;
import com.example.LearningNavigatorAPI.exceptions.StudentNotFoundException; // Import this
import com.example.LearningNavigatorAPI.models.LmsExam;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import com.example.LearningNavigatorAPI.repositories.LmsExamRepository;
import com.example.LearningNavigatorAPI.repositories.LmsStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl {
	 private final LmsExamRepository examRepository;
	    private final LmsStudentRepository studentRepository;

	    public ExamServiceImpl(LmsExamRepository examRepository, LmsStudentRepository studentRepository) {
	        this.examRepository = examRepository;
	        this.studentRepository = studentRepository;
	    }

	    public List<LmsExam> getAllExams() {
	        return examRepository.findAll();
	    }

	   
	    public LmsExam createExam(LmsExam exam) {
	        return examRepository.save(exam);
	    }

	    
	    public Optional<LmsExam> updateExam(Long examId, LmsExam examDetails) {
	        return examRepository.findById(examId)
	                .map(existingExam -> {
	                    existingExam.setSubject(examDetails.getSubject());
	                    // Update other fields as needed
	                    return examRepository.save(existingExam);
	                });
	    }

	    public void deleteExam(Long examId) {
	        examRepository.deleteById(examId);
	    }

	    
	    public void registerStudentForExam(Long examId, Long studentId) 
	            throws ExamNotFoundException, StudentNotFoundException {

	        LmsExam exam = examRepository.findById(examId)
	                .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID: " + examId));

	        LmsStudent student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));

	        // Check if the student is already registered for the exam (optional)
	        if (exam.getStudents().contains(student)) {
	            throw new IllegalArgumentException("Student is already registered for this exam.");
	        }

	        exam.getStudents().add(student);
	        examRepository.save(exam);
	    }
	    
	    
	    public LmsExam getExamById(Long examId) {
	        Optional<LmsExam> exam = examRepository.findById(examId);
	        if (exam.isPresent()) {
	            return exam.get();
	        } else {
	            throw new ExamNotFoundException("Exam not found with ID: " + examId); 
	        }
	    }
	 

}
