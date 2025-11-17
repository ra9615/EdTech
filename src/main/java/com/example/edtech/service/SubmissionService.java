package com.example.edtech.service;

import com.example.edtech.model.Submission;
import com.example.edtech.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> findById(Long id) {
        return submissionRepository.findById(id);
    }

    public List<Submission> findByAssignmentId(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    public List<Submission> findByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public Optional<Submission> findByAssignmentAndStudent(Long assignmentId, Long studentId) {
        return submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }

    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }

    public void deleteById(Long id) {
        submissionRepository.deleteById(id);
    }
}