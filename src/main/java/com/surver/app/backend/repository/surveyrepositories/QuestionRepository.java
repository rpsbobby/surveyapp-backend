package com.surver.app.backend.repository.surveyrepositories;

import com.surver.app.backend.entity.surveyentities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {


    List<Question> findAllBySurveyId(Long id);
}
