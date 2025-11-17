package com.example.edtech.service;

import com.example.edtech.dto.LessonDto;
import com.example.edtech.dto.ModuleDto;
import com.example.edtech.model.Course;
import com.example.edtech.model.Lesson;
import com.example.edtech.model.Module;
import com.example.edtech.repository.CourseRepository;
import com.example.edtech.repository.LessonRepository;
import com.example.edtech.repository.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CourseStructureService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    public CourseStructureService(CourseRepository courseRepository,
                                  ModuleRepository moduleRepository,
                                  LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.lessonRepository = lessonRepository;
    }

    @Transactional
    public Module addModuleToCourse(Long courseId, ModuleDto moduleDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Курс не найден"));

        Module module = new Module();
        module.setTitle(moduleDto.title());
        module.setOrderIndex(moduleDto.orderIndex());
        module.setDescription(moduleDto.description());
        module.setCourse(course);

        Module savedModule = moduleRepository.save(module);
        course.getModules().add(savedModule);

        return savedModule;
    }

    @Transactional
    public Lesson addLessonToModule(Long moduleId, LessonDto lessonDto) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Модуль не найден"));

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.title());
        lesson.setContent(lessonDto.content());
        lesson.setVideoUrl(lessonDto.videoUrl());
        lesson.setModule(module);

        Lesson savedLesson = lessonRepository.save(lesson);
        module.getLessons().add(savedLesson);

        return savedLesson;
    }
}
