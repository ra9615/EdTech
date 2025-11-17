package com.example.edtech.service;

import com.example.edtech.model.*;
import com.example.edtech.repository.AssignmentRepository;
import com.example.edtech.repository.LessonRepository;
import com.example.edtech.repository.SubmissionRepository;
import com.example.edtech.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             SubmissionRepository submissionRepository,
                             UserRepository userRepository,
                             LessonRepository lessonRepository) {
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Transactional
    public Assignment createAssignment(Long lessonId, Assignment assignment) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Урок не найден"));
        assignment.setLesson(lesson);
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public Submission submitAssignment(Long studentId, Long assignmentId, String content) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Студент не найден"));

        if (student.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("Пользователь не является студентом");
        }

        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException("Задание не найдено"));

        Optional<Submission> existing = submissionRepository
                .findByAssignmentIdAndStudentId(assignmentId, studentId);
        if (existing.isPresent()) {
            throw new IllegalStateException("Решение по заданию уже отправлено");
        }

        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setContent(content);
        submission.setSubmittedAt(LocalDateTime.now());

        return submissionRepository.save(submission);
    }

    @Transactional
    public Submission gradeSubmission(Long submissionId, Integer score, String feedback) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Решение не найдено"));

        submission.setScore(score);
        submission.setFeedback(feedback);

        return submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    public List<Submission> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }
}
