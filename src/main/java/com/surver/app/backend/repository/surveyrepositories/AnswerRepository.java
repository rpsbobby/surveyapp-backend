package com.surver.app.backend.repository.surveyrepositories;

import com.surver.app.backend.entity.surveyentities.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {


    List<Answer> findAllByQuestionId(Long questionId);

    void deleteAllByQuestionId(Long questionId);

}
