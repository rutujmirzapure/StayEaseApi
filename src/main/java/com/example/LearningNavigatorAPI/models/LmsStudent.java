package com.example.LearningNavigatorAPI.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LmsStudent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
    @JoinTable(
        name = "student_subject", 
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<LmsSubject> subjects = new ArrayList<>();

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<LmsExam> exams = new ArrayList<>();

    // Constructors 
    public LmsStudent() { 
        // Default constructor for JPA
    }

    public LmsStudent(String name) {
        this.name = name;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LmsSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<LmsSubject> subjects) {
        this.subjects = subjects;
    }

    public List<LmsExam> getExams() {
        return exams;
    }

    public void setExams(List<LmsExam> exams) {
        this.exams = exams;
    }

}
