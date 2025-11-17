package com.example.edtech.controller;

import com.example.edtech.model.AnswerOption;
import com.example.edtech.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answer-options")
@RequiredArgsConstructor
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    @GetMapping
    public List<AnswerOption> getAllAnswerOptions() {
        return answerOptionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerOption> getAnswerOptionById(@PathVariable Long id) {
        Optional<AnswerOption> answerOption = answerOptionService.findById(id);
        return answerOption.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerOption> getAnswerOptionsByQuestion(@PathVariable Long questionId) {
        return answerOptionService.findByQuestionId(questionId);
    }

    @PostMapping
    public AnswerOption createAnswerOption(@RequestBody AnswerOption answerOption) {
        return answerOptionService.save(answerOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerOption> updateAnswerOption(@PathVariable Long id, @RequestBody AnswerOption answerOption) {
        if (!answerOptionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        answerOption.setId(id);
        return ResponseEntity.ok(answerOptionService.save(answerOption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable Long id) {
        if (!answerOptionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        answerOptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
