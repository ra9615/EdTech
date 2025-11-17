package com.example.edtech.service;

import com.example.edtech.model.CourseReview;
import com.example.edtech.repository.CourseReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;

    public List<CourseReview> findAll() {
        return courseReviewRepository.findAll();
    }

    public Optional<CourseReview> findById(Long id) {
        return courseReviewRepository.findById(id);
    }

    public List<CourseReview> findByCourseId(Long courseId) {
        return courseReviewRepository.findByCourseId(courseId);
    }

    public List<CourseReview> findByStudentId(Long studentId) {
        return courseReviewRepository.findByStudentId(studentId);
    }

    public CourseReview save(CourseReview courseReview) {
        return courseReviewRepository.save(courseReview);
    }

    public void deleteById(Long id) {
        courseReviewRepository.deleteById(id);
    }
}