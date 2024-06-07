package com.example.LearningNavigatorAPI.testcontrollers;

import com.example.LearningNavigatorAPI.controllers.LmsExamController;
import com.example.LearningNavigatorAPI.models.LmsExam;
import com.example.LearningNavigatorAPI.services.ExamServiceImpl;
import com.example.LearningNavigatorAPI.services.LmsExamService;
import static org.hamcrest.Matchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LmsExamController.class)
public class ExamControllerTest {

	 @Autowired
	    private MockMvc mockMvc; 

	    @MockBean
	    private LmsExamService examService;

	    @Autowired
	    private ObjectMapper objectMapper; // For JSON serialization

	    @Test
	    public void testGetAllExams_Success() throws Exception {
	        LmsExam exam = new LmsExam(1L, "Test Subject");
	        List<LmsExam> examList = Collections.singletonList(exam);
	        when(examService.getAllExams()).thenReturn(examList);

	        mockMvc.perform(get("/exams")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].id", is(1)))
	                .andExpect(jsonPath("$[0].subject", is("Test Subject")));
	    }

	    @Test
	    public void testGetExamById_Success() throws Exception {
	        LmsExam exam = new LmsExam(1L, "Test Subject");
	        when(examService.getExamById(1L)).thenReturn(Optional.of(exam));

	        mockMvc.perform(get("/exams/{examId}", 1L)
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.id", is(1) ))
	                .andExpect(jsonPath("$.subject",is("Test Subject")));
	    }

	    @Test
	    public void testGetExamById_NotFound() throws Exception {
	        when(examService.getExamById(99L)).thenReturn(Optional.empty());

	        mockMvc.perform(get("/exams/{examId}", 99L)
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound()); 
	    }

	    @Test
	    public void testCreateExam_Success() throws Exception {
	        LmsExam newExam = new LmsExam("New Subject");
	        LmsExam createdExam = new LmsExam(1L, "New Subject"); 
	        when(examService.createExam(any(LmsExam.class))).thenReturn(createdExam);

	        mockMvc.perform(post("/exams")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(objectMapper.writeValueAsString(newExam)))
	                .andExpect(status().isOk()) // Or status().isCreated() if you prefer
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.subject", is("New Subject")));
	    }

	    // ... Add similar tests for updateExam, deleteExam, and registerStudentForExam ...
	}