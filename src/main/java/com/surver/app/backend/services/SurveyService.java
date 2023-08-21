package com.surver.app.backend.services;


import com.surver.app.backend.entity.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> findAll();

    Survey findById(long id);

    Survey addSurvey(Survey survey);

    void updateSurvey(Survey survey);

    void deleteSurveyById(long id);
}
