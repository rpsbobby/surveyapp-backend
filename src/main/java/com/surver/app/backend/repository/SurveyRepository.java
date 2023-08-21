package com.surver.app.backend.repository;


import com.surver.app.backend.entity.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
