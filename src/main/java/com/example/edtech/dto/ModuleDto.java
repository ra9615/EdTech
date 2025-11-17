package com.example.edtech.dto;

import java.util.List;

public record ModuleDto(
        String title,
        Integer orderIndex,
        String description,
        List<LessonDto> lessons
) {
}
