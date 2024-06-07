package com.example.LearningNavigatorAPI.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
public class LmsExam {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "exam_student",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<LmsStudent> students = new ArrayList<>();

    public LmsExam() {
        // Default constructor needed for JPA
    }

    public LmsExam(Long id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public LmsExam(String subject) {
        this.subject = subject;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<LmsStudent> getStudents() {
        return students;
    }

    public void setStudents(List<LmsStudent> students) {
        this.students = students;
    }

}
