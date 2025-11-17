package com.example.edtech.controller;

import com.example.edtech.model.Assignment;
import com.example.edtech.model.Submission;
import com.example.edtech.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/{lessonId}")
    public ResponseEntity<Assignment> createAssignment(@PathVariable Long lessonId,
                                                       @RequestBody Assignment assignment) {
        Assignment created = assignmentService.createAssignment(lessonId, assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{assignmentId}/submit/{studentId}")
    public ResponseEntity<Submission> submitAssignment(@PathVariable Long assignmentId,
                                                       @PathVariable Long studentId,
                                                       @RequestBody String content) {
        Submission submission = assignmentService.submitAssignment(studentId, assignmentId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(submission);
    }

    @PutMapping("/submissions/{submissionId}/grade")
    public ResponseEntity<Submission> gradeAssignment(@PathVariable Long submissionId,
                                                      @RequestParam Integer score,
                                                      @RequestParam(required = false) String feedback) {
        Submission updatedSubmission = assignmentService.gradeSubmission(submissionId, score, feedback);
        return ResponseEntity.ok(updatedSubmission);
    }

    @GetMapping("/{assignmentId}/submissions")
    public List<Submission> getAllSubmissions(@PathVariable Long assignmentId) {
        return assignmentService.getSubmissionsByAssignment(assignmentId);
    }

    @GetMapping("/students/{studentId}/submissions")
    public List<Submission> getStudentSubmissions(@PathVariable Long studentId) {
        return assignmentService.getSubmissionsByStudent(studentId);
    }
}
