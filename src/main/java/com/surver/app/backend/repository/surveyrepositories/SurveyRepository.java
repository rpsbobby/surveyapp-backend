package com.surver.app.backend.repository.surveyrepositories;


import com.surver.app.backend.entity.surveyentities.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
