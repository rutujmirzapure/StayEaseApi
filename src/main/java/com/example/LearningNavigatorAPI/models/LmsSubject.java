package com.example.LearningNavigatorAPI.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class LmsSubject {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
	    private List<LmsStudent> students = new ArrayList<>();

	    // Constructors
	    public LmsSubject() {
	        // Default constructor for JPA
	    }

	    public LmsSubject(String name) {
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

	    public List<LmsStudent> getStudents() {
	        return students;
	    }

	    public void setStudents(List<LmsStudent> students) {
	        this.students = students;
	    }

}
