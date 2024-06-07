package com.example.LearningNavigatorAPI.controllers;
import com.example.LearningNavigatorAPI.exceptions.ExamNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsExam;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import com.example.LearningNavigatorAPI.services.LmsExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class LmsExamController {
	
	  private final LmsExamService examService;

	    public LmsExamController(LmsExamService examService) {
	        this.examService = examService;
	    }

	    @GetMapping
	    public List<LmsExam> getAllExams() {
	        return examService.getAllExams();
	    }

	    @GetMapping("/{examId}")
	    public LmsExam getExamById(@PathVariable Long examId) {
	        return examService.getExamById(examId)
	                .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID: " + examId));
	    }

	    @PostMapping
	    public LmsExam createExam(@RequestBody LmsExam exam) {
	        return examService.createExam(exam);
	    }

	    @PutMapping("/{examId}")
	    public LmsExam updateExam(@PathVariable Long examId, @RequestBody Exam examDetails) {
	        return examService.updateExam(examId, examDetails)
	                .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID: " + examId));
	    }

	    @DeleteMapping("/{examId}")
	    public ResponseEntity<HttpStatus> deleteExam(@PathVariable Long examId) {
	        examService.deleteExam(examId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    // Endpoint to register a student for an exam
	    @PostMapping("/{examId}/students/{studentId}")
	    public ResponseEntity<String> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
	        try {
	            examService.registerStudentForExam(examId, studentId);
	            return ResponseEntity.ok("Student registered for exam successfully!"); 
	        } catch (ExamNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } 
	    }

}
