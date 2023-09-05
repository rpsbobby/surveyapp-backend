package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Question;
import com.surver.app.backend.services.surveyservices.QuestionService;
import com.surver.app.backend.services.surveyservices.SurveyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceImplTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private QuestionService questionService;


    @Value("${sql.insert.into.survey.1}")
    private String addSurvey1;
    @Value("${sql.insert.into.question.1}")
    private String addQuestion1;
    @Value("${sql.insert.into.answer.1}")
    private String addAnswer1;
    @Value("${sql.insert.into.question.2}")
    private String addQuestion2;
    @Value("${sql.insert.into.answer.2}")
    private String addAnswer2;

    @Value("${sql.delete.survey}")
    private String deleteSurvey;
    @Value("${sql.delete.question}")
    private String deleteQuestion;
    @Value("${sql.delete.answer}")
    private String deleteAnswer;

    @BeforeEach
    void beforeEach() {
        jdbc.execute(addSurvey1);
        jdbc.execute(addQuestion1);
        jdbc.execute(addAnswer1);
        jdbc.execute(addQuestion2);
        jdbc.execute(addAnswer2);
    }

    @AfterEach
    void afterEach() {
        jdbc.execute(deleteAnswer);
        jdbc.execute(deleteQuestion);
        jdbc.execute(deleteSurvey);
    }

    @Test
    void findAllBySurveyId() {
        List<Question> temp = questionService.findAllBySurveyId(10);
        assertEquals(2, temp.size());
    }

    @Test
    void deleteById() {
        questionService.deleteById(10);
        Question q = questionService.findById(10);
        assertNull(q);
    }

    @Test
    void update() {
        Question temp = questionService.findById(10);
        temp.setQuestion("Test");
        questionService.update(temp);
        temp = questionService.findById(10);
        assertEquals("Test",temp.getQuestion());
    }
}