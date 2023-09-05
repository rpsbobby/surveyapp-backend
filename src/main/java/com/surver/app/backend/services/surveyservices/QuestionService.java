package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAllBySurveyId(long id);

    Question findById(long id);

    void deleteById(long id);

    void update(Question question);


}
