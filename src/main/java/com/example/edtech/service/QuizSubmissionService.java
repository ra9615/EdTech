package com.example.edtech.service;

import com.example.edtech.model.QuizSubmission;
import com.example.edtech.repository.QuizSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizSubmissionService {

    private final QuizSubmissionRepository quizSubmissionRepository;

    public List<QuizSubmission> findAll() {
        return quizSubmissionRepository.findAll();
    }

    public Optional<QuizSubmission> findById(Long id) {
        return quizSubmissionRepository.findById(id);
    }

    public List<QuizSubmission> findByStudentId(Long studentId) {
        return quizSubmissionRepository.findByStudentId(studentId);
    }

    public List<QuizSubmission> findByQuizId(Long quizId) {
        return quizSubmissionRepository.findByQuizId(quizId);
    }

    public Optional<QuizSubmission> findByQuizAndStudent(Long quizId, Long studentId) {
        return quizSubmissionRepository.findByQuizIdAndStudentId(quizId, studentId);
    }

    public QuizSubmission save(QuizSubmission quizSubmission) {
        return quizSubmissionRepository.save(quizSubmission);
    }

    public void deleteById(Long id) {
        quizSubmissionRepository.deleteById(id);
    }
}