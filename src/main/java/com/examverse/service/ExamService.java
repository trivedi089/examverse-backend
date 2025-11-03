package com.examverse.service;

import com.examverse.exception.ResourceNotFoundException;
import com.examverse.model.Exam;
import com.examverse.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID " + id));
    }

    public Exam createExam(Exam exam) {
        if (exam.getName() == null || exam.getName().isBlank()) {
            throw new IllegalArgumentException("Exam name cannot be empty");
        }
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exam not found with ID " + id);
        }
        examRepository.deleteById(id);
    }
}