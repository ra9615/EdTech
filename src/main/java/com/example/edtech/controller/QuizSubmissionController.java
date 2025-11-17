package com.example.edtech.controller;

import com.example.edtech.model.QuizSubmission;
import com.example.edtech.service.QuizSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz-submissions")
@RequiredArgsConstructor
public class QuizSubmissionController {

    private final QuizSubmissionService quizSubmissionService;

    @GetMapping
    public List<QuizSubmission> getAllQuizSubmissions() {
        return quizSubmissionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizSubmission> getQuizSubmissionById(@PathVariable Long id) {
        Optional<QuizSubmission> quizSubmission = quizSubmissionService.findById(id);
        return quizSubmission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public List<QuizSubmission> getQuizSubmissionsByStudent(@PathVariable Long studentId) {
        return quizSubmissionService.findByStudentId(studentId);
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuizSubmission> getQuizSubmissionsByQuiz(@PathVariable Long quizId) {
        return quizSubmissionService.findByQuizId(quizId);
    }

    @GetMapping("/quiz/{quizId}/student/{studentId}")
    public ResponseEntity<QuizSubmission> getQuizSubmissionByQuizAndStudent(
            @PathVariable Long quizId, @PathVariable Long studentId) {
        Optional<QuizSubmission> quizSubmission = quizSubmissionService.findByQuizAndStudent(quizId, studentId);
        return quizSubmission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public QuizSubmission createQuizSubmission(@RequestBody QuizSubmission quizSubmission) {
        return quizSubmissionService.save(quizSubmission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizSubmission> updateQuizSubmission(@PathVariable Long id, @RequestBody QuizSubmission quizSubmission) {
        if (quizSubmissionService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quizSubmission.setId(id);
        return ResponseEntity.ok(quizSubmissionService.save(quizSubmission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizSubmission(@PathVariable Long id) {
        if (quizSubmissionService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quizSubmissionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}