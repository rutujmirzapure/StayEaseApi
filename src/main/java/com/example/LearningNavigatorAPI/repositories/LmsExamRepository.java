package com.example.LearningNavigatorAPI.repositories;

import com.example.LearningNavigatorAPI.models.LmsExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LmsExamRepository extends JpaRepository<LmsExam, Long> {
}
