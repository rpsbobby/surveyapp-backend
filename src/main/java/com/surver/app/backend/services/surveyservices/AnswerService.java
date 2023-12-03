package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerService {
    List<Answer> findAllAnswersByQuestionId(Long questionId);
    Answer addAnswer(Answer answer);
    void addAllAnswers(List<Answer> answers);
    void updateAnswer(Answer answer);
    void deleteAnswerById(Long answerId);
    void deleteAllAnswersByQuestionId(Long questionId);
}
