package com.example.edtech.service;

import com.example.edtech.model.*;
import com.example.edtech.repository.CourseRepository;
import com.example.edtech.repository.EnrollmentRepository;
import com.example.edtech.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Enrollment enrollStudent(Long courseId, Long studentId) {
        if (enrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId)) {
            throw new IllegalStateException("Студент уже записан на этот курс");
        }

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Студент не найден"));

        if (student.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("Пользователь не является студентом");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Курс не найден"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unenrollStudent(Long courseId, Long studentId) {
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndStudentId(courseId, studentId)
                .orElseThrow(() -> new EntityNotFoundException("Запись на курс не найдена"));

        enrollmentRepository.delete(enrollment);
    }

    public List<User> getStudentsByCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        return enrollments.stream()
                .map(Enrollment::getStudent)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

    public Optional<Enrollment> findByCourseIdAndStudentId(Long studentId, Long courseId) {
        return enrollmentRepository.findByCourseIdAndStudentId(studentId, courseId);
    }

    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}
