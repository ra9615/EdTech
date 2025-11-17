package com.example.edtech.service;

import com.example.edtech.dto.CourseDto;
import com.example.edtech.dto.LessonDto;
import com.example.edtech.dto.ModuleDto;
import com.example.edtech.model.*;
import com.example.edtech.model.Module;
import com.example.edtech.repository.CategoryRepository;
import com.example.edtech.repository.CourseRepository;
import com.example.edtech.repository.EnrollmentRepository;
import com.example.edtech.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository,
                         CategoryRepository categoryRepository,
                         UserRepository userRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public Course createCourse(CourseDto courseDto) {
        Category category = categoryRepository.findById(courseDto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        User teacher = userRepository.findById(courseDto.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Учитель не найден"));

        if (teacher.getRole() != Role.TEACHER) {
            throw new IllegalArgumentException("Пользователь не является преподавателем");
        }

        Course course = new Course();
        course.setTitle(courseDto.title());
        course.setDescription(courseDto.description());
        course.setCategory(category);
        course.setTeacher(teacher);
        course.setDuration(courseDto.duration());

        if (courseDto.modules() != null) {
            List<Module> modules = courseDto.modules().stream()
                    .map(this::convertToModule)
                    .collect(Collectors.toList());

            modules.forEach(module -> module.setCourse(course));
            course.setModules(modules);
        }

        return courseRepository.save(course);
    }

    private Module convertToModule(ModuleDto moduleDto) {
        Module module = new Module();
        module.setTitle(moduleDto.title());
        module.setOrderIndex(moduleDto.orderIndex());
        module.setDescription(moduleDto.description());

        if (moduleDto.lessons() != null) {
            List<Lesson> lessons = moduleDto.lessons().stream()
                    .map(this::convertToLesson)
                    .collect(Collectors.toList());
            lessons.forEach(lesson -> lesson.setModule(module));
            module.setLessons(lessons);
        }
        return module;
    }

    private Lesson convertToLesson(LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.title());
        lesson.setContent(lessonDto.content());
        lesson.setVideoUrl(lessonDto.videoUrl());
        return lesson;
    }


    @Transactional
    public Course updateCourse(Long courseId, CourseDto courseDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Курс не найден"));

        if (courseDto.title() != null) {
            course.setTitle(courseDto.title());
        }
        if (courseDto.description() != null) {
            course.setDescription(courseDto.description());
        }
        if (courseDto.categoryId() != null) {
            Category category = categoryRepository.findById(courseDto.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));
            course.setCategory(category);
        }
        if (courseDto.teacherId() != null) {
            User teacher = userRepository.findById(courseDto.teacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Учитель не найден"));
            course.setTeacher(teacher);
        }
        if (courseDto.duration() != null) {
            course.setDuration(courseDto.duration());
        }
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Курс не найден"));

        boolean hasEnrollments = !enrollmentRepository.findByCourseId(courseId).isEmpty();

        if (hasEnrollments) {
            throw new IllegalStateException("Нельзя удалить курс, на который записаны студенты");
        }
        courseRepository.delete(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findByTeacherId(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }
}

