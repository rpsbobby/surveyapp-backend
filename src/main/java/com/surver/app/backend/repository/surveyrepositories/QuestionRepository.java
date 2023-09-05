package com.surver.app.backend.repository.surveyrepositories;

import com.surver.app.backend.entity.surveyentities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
