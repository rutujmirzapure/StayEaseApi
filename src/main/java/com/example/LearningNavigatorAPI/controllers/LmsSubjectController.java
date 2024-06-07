package com.example.LearningNavigatorAPI.controllers;

import com.example.LearningNavigatorAPI.exceptions.SubjectNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsSubject;
import com.example.LearningNavigatorAPI.services.LmsSubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class LmsSubjectController {
	
	private final LmsSubjectService subjectService;

    public LmsSubjectController(LmsSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<LmsSubject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{subjectId}")
    public LmsSubject getSubjectById(@PathVariable Long subjectId) {
        return subjectService.getSubjectById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID: " + subjectId));
    }

    @PostMapping
    public LmsSubject createSubject(@RequestBody LmsSubject subject) {
        return subjectService.createSubject(subject);
    }

    @PutMapping("/{subjectId}")
    public LmsSubject updateSubject(@PathVariable Long subjectId, @RequestBody Subject subjectDetails) {
        return subjectService.updateSubject(subjectId, subjectDetails)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID: " + subjectId));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add other endpoints as needed, e.g.:
    // POST /subjects/{subjectId}/students/{studentId} - To enroll a student in a subject
    @PostMapping("/{subjectId}/students/{studentId}")
    public ResponseEntity<String> enrollStudent(
            @PathVariable Long subjectId, 
            @PathVariable Long studentId
    ) {
        try {
            subjectService.enrollStudent(subjectId, studentId);
            return ResponseEntity.ok("Student enrolled in subject successfully!");
        } catch (SubjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } 
        // Catch and handle other potential exceptions (e.g., StudentNotFoundException)
    }

}
