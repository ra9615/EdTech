package com.example.edtech.repository;

import com.example.edtech.model.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {

    List<QuizSubmission> findByStudentId(Long studentId);

    List<QuizSubmission> findByQuizId(Long quizId);

    List<QuizSubmission> findByQuizModuleCourseId(Long courseId);

    Optional<QuizSubmission> findByQuizIdAndStudentId(Long quizId, Long studentId);
}
