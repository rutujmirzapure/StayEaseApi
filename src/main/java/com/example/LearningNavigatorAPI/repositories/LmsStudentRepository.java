package com.example.LearningNavigatorAPI.repositories;
import com.example.LearningNavigatorAPI.models.LmsStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LmsStudentRepository extends JpaRepository<LmsStudent, Long> {
}
