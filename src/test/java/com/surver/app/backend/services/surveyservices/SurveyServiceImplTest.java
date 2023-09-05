package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.services.surveyservices.SurveyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyServiceImplTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private SurveyService service;


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
    @DisplayName("Test find all method when data in DB")
    void testFindAll() {
        assertEquals(1, service.findAll().size());
        jdbc.execute(deleteAnswer);
        jdbc.execute(deleteQuestion);
        jdbc.execute(deleteSurvey);
        assertEquals(0,service.findAll().size());
    }

    @Test
    @DisplayName("Test find by id when data in DB - integration test")
    void testFindById() {
        Survey survey = service.findById(10);
        assertNotNull(survey);
        assertEquals(survey.getId(), 10);
    }

    @Test
    @DisplayName("Test Find By with incorrect id")
    void testFindByIdWithIncorrectId() {
        Survey survey = service.findById(100);
        assertNull(survey);
    }

    @Test
    @DisplayName("Test delete integration test")
    void testDelete() {
        service.deleteSurveyById(10);
        Survey survey = service.findById(10);
        assertNull(survey);
    }

    @Test
    @DisplayName("Test delete with incorrect id")
    void testDeleteWithIncorrectId() {
        service.deleteSurveyById(10);
    }

    @Test
    @DisplayName("Test Add New Survey")
    void testAddNewSurvey() {
        Survey survey = new Survey();
        survey.setTitle("Third");
        Survey test = service.addSurvey(survey);
        assertNotNull(test);
        assertNotNull(service.findById(test.getId()));
        Survey survey1 = new Survey();
        survey.setTitle("Fourth");
        Survey test2 = service.addSurvey(survey1);
        assertNotNull(test);
        assertNotNull(service.findById(test2.getId()));
    }

    @Test
    @DisplayName("Test Update Survey")
    void testUpdateSurvey() {
        Survey temp = service.findById(10);
        temp.setTitle("Changed");
        service.updateSurvey(temp);
        temp = service.findById(10);
        assertEquals("Changed", temp.getTitle());
    }




}