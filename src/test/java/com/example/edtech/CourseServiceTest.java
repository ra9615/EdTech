
package com.example.edtech;

import com.example.edtech.model.Course;
import com.example.edtech.repository.CourseRepository;
import com.example.edtech.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void findAll_ReturnsAllCourses() {
        Course course1 = new Course();
        Course course2 = new Course();
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        List<Course> result = courseService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findByTeacherId_ExistingTeacher_ReturnsCourses() {
        Course course = new Course();
        when(courseRepository.findByTeacherId(1L)).thenReturn(Arrays.asList(course));

        List<Course> result = courseService.findByTeacherId(1L);

        assertFalse(result.isEmpty());
    }
}