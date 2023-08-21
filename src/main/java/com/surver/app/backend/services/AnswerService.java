package com.surver.app.backend.services;

import com.surver.app.backend.entity.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findAllAnswersByQuestionId(Long questionId);
    void addAnswer(Answer answer);
    void addAllAnswers(List<Answer> answers);
    void updateAnswer(Answer answer);
    void deleteAnswerById(Long answerId);
    void deleteAllAnswersByQuestionId(Long questionId);
}
