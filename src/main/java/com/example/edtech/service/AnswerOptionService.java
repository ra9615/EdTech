package com.example.edtech.service;

import com.example.edtech.model.AnswerOption;
import com.example.edtech.repository.AnswerOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;

    public List<AnswerOption> findAll() {
        return answerOptionRepository.findAll();
    }

    public Optional<AnswerOption> findById(Long id) {
        return answerOptionRepository.findById(id);
    }

    public List<AnswerOption> findByQuestionId(Long questionId) {
        return answerOptionRepository.findByQuestionId(questionId);
    }

    public AnswerOption save(AnswerOption answerOption) {
        return answerOptionRepository.save(answerOption);
    }

    public void deleteById(Long id) {
        answerOptionRepository.deleteById(id);
    }
}
