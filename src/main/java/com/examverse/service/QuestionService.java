package com.examverse.service;

import com.examverse.model.Question;
import com.examverse.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getQuestionsByExam(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
}