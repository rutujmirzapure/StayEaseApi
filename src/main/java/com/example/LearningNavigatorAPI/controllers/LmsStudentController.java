package com.example.LearningNavigatorAPI.controllers;

import com.example.LearningNavigatorAPI.exceptions.StudentNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import com.example.LearningNavigatorAPI.services.LmsStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class LmsStudentController {
	
	 private final LmsStudentService studentService;

	    public LmsStudentController(LmsStudentService studentService) {
	        this.studentService = studentService;
	    }

	    @GetMapping
	    public List<LmsStudent> getAllStudents() {
	        return studentService.getAllStudents();
	    }

	    @GetMapping("/{studentId}")
	    public LmsStudent getStudentById(@PathVariable Long studentId) {
	        return studentService.getStudentById(studentId)
	                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));
	    }

	    @PostMapping
	    public LmsStudent createStudent(@RequestBody LmsStudent student) {
	        return studentService.createStudent(student);
	    }

	    @PutMapping("/{studentId}")
	    public LmsStudent updateStudent(@PathVariable Long studentId, @RequestBody Student studentDetails) {
	        return studentService.updateStudent(studentId, studentDetails)
	                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));
	    }

	    @DeleteMapping("/{studentId}")
	    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long studentId) {
	        studentService.deleteStudent(studentId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    // Add other endpoints for student-related actions, e.g., 
	    // enrolling in a subject could be a POST request to /students/{studentId}/subjects/{subjectId}

}
