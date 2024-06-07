package com.example.LearningNavigatorAPI.services;

import com.example.LearningNavigatorAPI.exceptions.StudentNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsStudent;

import java.util.List;
import java.util.Optional;
public interface LmsStudentService {
	
	 List<LmsStudent> getAllStudents();
	    Optional<LmsStudent> getStudentById(Long studentId);
	    LmsStudent createStudent(LmsStudent student);
	    Optional<LmsStudent> updateStudent(Long studentId, LmsStudent studentDetails);
	    void deleteStudent(Long studentId);

}
