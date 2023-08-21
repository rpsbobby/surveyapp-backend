package com.surver.app.backend.services;

import com.surver.app.backend.entity.Survey;
import com.surver.app.backend.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository dao;

    public SurveyServiceImpl(SurveyRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<Survey> findAll() {
        List<Survey> temp = new ArrayList<>();
        dao.findAll().forEach(temp::add);
        if (temp.size() == 0) return Collections.emptyList();
        return temp;
    }

    @Override
    public Survey findById(long id) {
        Optional<Survey> temp = dao.findById(id);
        return temp.orElse(null);
    }

    @Override
    public Survey addSurvey(Survey survey) {
        return dao.save(survey);
    }

    @Override
    public void updateSurvey(Survey survey) {
        dao.save(survey);
    }

    @Override
    public void deleteSurveyById(long id) {
        dao.deleteById(id);
    }
}
