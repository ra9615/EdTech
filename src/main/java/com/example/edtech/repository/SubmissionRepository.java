package com.example.edtech.repository;

import com.example.edtech.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Optional<Submission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);

    List<Submission> findByAssignmentId(Long assignmentId);

    List<Submission> findByStudentId(Long studentId);

    @Query("SELECT s FROM Submission s JOIN FETCH s.assignment WHERE s.student.id = :studentId")
    List<Submission> findAllByStudentIdWithAssignment(@Param("studentId") Long studentId);
}
