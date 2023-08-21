package com.surver.app.backend.repository;

import com.surver.app.backend.entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    List<Answer> findAllByQuestionId(Long questionId);
    void deleteAllByQuestionId(Long questionId);

}
