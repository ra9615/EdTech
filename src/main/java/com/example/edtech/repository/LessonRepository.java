package com.example.edtech.repository;

import com.example.edtech.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleCourseId(Long courseModuleId);
}

