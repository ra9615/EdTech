package com.example.edtech.controller;

import com.example.edtech.dto.QuizAnswerDto;
import com.example.edtech.model.Quiz;
import com.example.edtech.model.QuizSubmission;
import com.example.edtech.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/{quizId}/take/{studentId}")
    public ResponseEntity<QuizSubmission> takeQuiz(@PathVariable Long quizId,
                                                   @PathVariable Long studentId,
                                                   @RequestBody List<QuizAnswerDto> answers) {
        QuizSubmission submission = quizService.takeQuiz(studentId, quizId, answers);
        return ResponseEntity.ok(submission);
    }

    @GetMapping("/student/{studentId}/submissions")
    public List<QuizSubmission> getSubmissionsByStudent(@PathVariable Long studentId) {
        return quizService.getSubmissionsByStudent(studentId);
    }

    @GetMapping("/course/{courseId}/submissions")
    public List<QuizSubmission> getSubmissionsByCourse(@PathVariable Long courseId) {
        return quizService.getSubmissionsByCourse(courseId);
    }
}