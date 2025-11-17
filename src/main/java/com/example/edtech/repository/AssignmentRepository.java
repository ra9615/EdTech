package com.example.edtech.repository;

import com.example.edtech.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByLesson_Id(Long lessonId);
}
