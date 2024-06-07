package com.example.LearningNavigatorAPI.repositories;


import com.example.LearningNavigatorAPI.models.LmsSubject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LmsSubjectRepository extends JpaRepository<LmsSubject, Long> {
	
} 