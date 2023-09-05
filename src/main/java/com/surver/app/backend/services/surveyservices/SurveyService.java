package com.surver.app.backend.services.surveyservices;


import com.surver.app.backend.entity.surveyentities.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> findAll();

    Survey findById(long id);

    Survey addSurvey(Survey survey);

    void updateSurvey(Survey survey);

    void deleteSurveyById(long id);
}
