package com.surver.app.backend.repository.surveyrepositories;


import com.surver.app.backend.entity.surveyentities.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    List<Survey> findAll();
    List<Survey> findAllByCreator(String creator);
}
