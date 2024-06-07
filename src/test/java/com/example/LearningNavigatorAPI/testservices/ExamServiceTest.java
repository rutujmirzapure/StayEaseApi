package com.example.LearningNavigatorAPI.testservices;

import com.example.LearningNavigatorAPI.exceptions.ExamNotFoundException;
import com.example.LearningNavigatorAPI.exceptions.StudentNotFoundException;
import com.example.LearningNavigatorAPI.models.LmsExam;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import com.example.LearningNavigatorAPI.repositories.LmsExamRepository;
import com.example.LearningNavigatorAPI.repositories.LmsStudentRepository;
import com.example.LearningNavigatorAPI.services.ExamServiceImpl;
import com.example.LearningNavigatorAPI.services.LmsExamService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {

    @Mock
    private LmsExamRepository examRepository;

    @Mock 
    private LmsStudentRepository studentRepository; 

    @InjectMocks
    private LmsExamService examService; 

    @Test
    public void testGetAllExams() {
        LmsExam exam = new LmsExam(1L, "Test Subject");
        when(examRepository.findAll()).thenReturn(Collections.singletonList(exam));
        List<LmsExam> exams = examService.getAllExams();
        assertEquals(1, exams.size());
        assertEquals("Test Subject", exams.get(0).getSubject());
    }

    @Test
    public void testGetExamById_Success() {
        LmsExam exam = new LmsExam(1L, "Test Subject");
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        Optional<LmsExam> foundExam = examService.getExamById(1L);

        assertTrue(foundExam.isPresent());
        assertEquals("Test Subject", foundExam.get().getSubject());
    }

    @Test
    public void testGetExamById_NotFound() {
        when(examRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<LmsExam> foundExam = examService.getExamById(99L);
        assertTrue(foundExam.isEmpty()); 
    }

    @Test
    public void testCreateExam() {
        LmsExam exam = new LmsExam("Test Subject");
        when(examRepository.save(any(LmsExam.class))).thenAnswer(invocation -> {
            LmsExam savedExam = invocation.getArgument(0);
            savedExam.setId(1L); // Simulate ID generation
            return savedExam;
        });

        LmsExam createdExam = examService.createExam(exam);

        assertEquals("Test Subject", createdExam.getSubject());
        assertNotNull(createdExam.getId()); 
    }

    // ... Add similar tests for updateExam, deleteExam ... 

    @Test
    public void testRegisterStudentForExam_Success() throws Exception {
        LmsExam exam = new LmsExam(1L, "Test Subject");
        LmsStudent student = new LmsStudent("Test Student");
        student.setId(1L); 
        exam.setStudents(Collections.singletonList(student));

        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(examRepository.save(any(LmsExam.class))).thenReturn(exam); 

        examService.registerStudentForExam(1L, 1L); 

        verify(examRepository, times(1)).save(exam);
    }


    @Test
    public void testRegisterStudentForExam_ExamNotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty()); 
        assertThrows(ExamNotFoundException.class, () -> {
            examService.registerStudentForExam(1L, 1L);
        });
    }

    @Test
    public void testRegisterStudentForExam_StudentNotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.of(new Exam()));
        when(studentRepository.findById(1L)).thenReturn(Optional.empty()); 

        assertThrows(StudentNotFoundException.class, () -> {
            examService.registerStudentForExam(1L, 1L); 
        });
    }
}
