package com.example.edtech.service;

import com.example.edtech.dto.QuizAnswerDto;
import com.example.edtech.model.*;
import com.example.edtech.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository,
                       QuizSubmissionRepository quizSubmissionRepository,
                       QuestionRepository questionRepository,
                       AnswerOptionRepository answerOptionRepository,
                       UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.questionRepository = questionRepository;
        this.answerOptionRepository = answerOptionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public QuizSubmission takeQuiz(Long studentId, Long quizId, List<QuizAnswerDto> answers) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Студент не найден"));

        if (student.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("Пользователь не является студентом");
        }

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Тест не найден"));

        List<Question> questions = questionRepository.findByQuizId(quizId);
        Map<Long, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        int totalQuestions = questions.size();
        int correctAnswersCount = 0;

        for (QuizAnswerDto answerDto : answers) {
            Question question = questionMap.get(answerDto.questionId());
            if (question == null) continue;

            List<AnswerOption> correctOptions = question.getOptions().stream()
                    .filter(AnswerOption::getIsCorrect).toList();

            Set<Long> correctOptionIds = correctOptions.stream()
                    .map(AnswerOption::getId)
                    .collect(Collectors.toSet());

            if (new HashSet<>(answerDto.selectedOptionIds()).equals(correctOptionIds)) {
                correctAnswersCount++;
            }
        }

        double score = totalQuestions == 0 ? 0.0 : ((double) correctAnswersCount / totalQuestions) * 100;

        QuizSubmission submission = new QuizSubmission();
        submission.setStudent(student);
        submission.setQuiz(quiz);
        submission.setScore(score);
        submission.setTakenAt(LocalDateTime.now());

        return quizSubmissionRepository.save(submission);
    }

    public List<QuizSubmission> getSubmissionsByStudent(Long studentId) {
        return quizSubmissionRepository.findByStudentId(studentId);
    }

    public List<QuizSubmission> getSubmissionsByCourse(Long courseId) {
        return quizSubmissionRepository.findByQuizModuleCourseId(courseId);
    }
}

