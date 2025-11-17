package com.example.edtech.controller;

import com.example.edtech.model.Course;
import com.example.edtech.model.Enrollment;
import com.example.edtech.model.User;
import com.example.edtech.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Enrollment> enrollStudent(@PathVariable Long courseId,
                                                    @PathVariable Long studentId) {
        Enrollment enrollment = enrollmentService.enrollStudent(courseId, studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Void> unrollStudent(@PathVariable Long courseId,
                                              @PathVariable Long studentId) {
        enrollmentService.unenrollStudent(courseId, studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/{courseId}/students")
    public List<User> getStudentsByCourse(@PathVariable Long courseId) {
        return enrollmentService.getStudentsByCourse(courseId);
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getCoursesByStudent(@PathVariable Long studentId) {
        return enrollmentService.getCoursesByStudent(studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return enrollmentService.findByStudentId(studentId);
    }
}