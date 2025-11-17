package com.example.edtech.dto;

import java.util.Set;

public record QuizAnswerDto(
        Long questionId,

        Set<Long> selectedOptionIds
) {
}
