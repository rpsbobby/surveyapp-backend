package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.repository.surveyrepositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;

    public SurveyServiceImpl(SurveyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Survey> findAll() {
        return repository.findAll();
    }

    @Override
    public Survey findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Survey save(Survey survey) {
        return repository.save(survey);
    }

    @Override
    public void updateSurvey(Survey survey) {
        repository.save(survey);
    }

    @Override
    public void deleteSurveyById(long id) {
        repository.deleteById(id);
    }


}
