package com.example.edtech.dto;

import java.util.List;

public record CourseDto(
        String title,
        String description,
        Long categoryId,
        Long teacherId,
        Integer duration,
        List<ModuleDto> modules
) {
}
