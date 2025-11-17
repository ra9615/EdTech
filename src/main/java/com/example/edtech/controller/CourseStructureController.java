package com.example.edtech.controller;

import com.example.edtech.dto.LessonDto;
import com.example.edtech.dto.ModuleDto;
import com.example.edtech.model.Lesson;
import com.example.edtech.model.Module;
import com.example.edtech.service.CourseStructureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseStructureController {

    private final CourseStructureService courseStructureService;

    public CourseStructureController(CourseStructureService courseStructureService) {
        this.courseStructureService = courseStructureService;
    }

    @PostMapping("/{courseId}/modules")
    public ResponseEntity<Module> addModule(@PathVariable Long courseId,
                                            @RequestBody ModuleDto moduleDto) {
        Module module = courseStructureService.addModuleToCourse(courseId, moduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(module);
    }

    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Lesson> addLesson(@PathVariable Long moduleId,
                                            @RequestBody  LessonDto lessonDto) {
        Lesson lesson = courseStructureService.addLessonToModule(moduleId, lessonDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(lesson);
    }
}
