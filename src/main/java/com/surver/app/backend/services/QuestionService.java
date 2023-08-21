package com.surver.app.backend.services;

import com.surver.app.backend.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAllBySurveyId(long id);

    Question findById(long id);

    void deleteById(long id);

    void update(Question question);


}
