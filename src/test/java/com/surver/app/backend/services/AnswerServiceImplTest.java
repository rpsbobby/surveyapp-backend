package com.surver.app.backend.services;

import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.repository.AnswerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnswerServiceImplTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository repository;

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

    @Autowired
    private Environment environment;

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
    @Order(1)
    void findAllAnswersByQuestionId() {
        assertEquals(1, answerService.findAllAnswersByQuestionId(10L).size());
    }


    @Test
    @Order(2)
    void updateAnswer() {
        Answer answer = repository.findById(10L).orElse(null);
        assertNotNull(answer);
        answer.setQuestion(questionService.findById(10L));
        answer.setAnswer("Test");
        answerService.updateAnswer(answer);
        assertEquals(1, answerService.findAllAnswersByQuestionId(10L).size());
        answer = repository.findById(10L).orElse(null);
        assertNotNull(answer);
        assertEquals("Test", answer.getAnswer());

    }

    @Test
    @Order(3)
    void addAnswer() {
        Answer answer = new Answer();
        answer.setQuestion(questionService.findById(10L));
        answer.setAnswer("Test");
        answerService.addAnswer(answer);
        assertEquals(2, answerService.findAllAnswersByQuestionId(10L).size());

    }


    @Test
    @Order(4)
    void deleteAnswerById() {
        assertNotNull(repository.findById(10L).get());
        answerService.deleteAnswerById(10L);
        assertNull(repository.findById(10L).orElse(null));
    }

    @Test
    @Order(5)
    void addAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
//        answer1.setQuestion(questionService.findById(10L));
        answer1.setAnswer("Test1");
        Answer answer2 = new Answer();
//        answer2.setQuestion(questionService.findById(20L));
        answer2.setAnswer("Test2");
        answers.add(answer1);
        answers.add(answer2);

        answerService.addAllAnswers(answers);
        assertEquals(2, answerService.findAllAnswersByQuestionId(10L).size());
        assertEquals(2, answerService.findAllAnswersByQuestionId(20L).size());


    }

    @Test
    @Order(6)
    void deleteAllAnswersByQuestionId() {
        int size = repository.findAllByQuestionId(10L).size();
        assertNotEquals(0, size);
        answerService.deleteAllAnswersByQuestionId(10L);
        assertNotEquals(size, answerService.findAllAnswersByQuestionId(10L).size());

    }
}