package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.entity.surveyentities.Question;
import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.repository.surveyrepositories.AnswerRepository;
import com.surver.app.backend.repository.surveyrepositories.SurveyRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceIntegrationTest {

    //    @Autowired
    private SurveyRepository surveyRepository;
    //    @Autowired
    private QuestionService questionRepository;
    //    @Autowired
    private AnswerRepository answerRepository;


    @Autowired
    public QuestionServiceIntegrationTest(SurveyRepository surveyRepository,
                                          QuestionService questionRepository,
                                          AnswerRepository answerRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    private Question question1;
    private Question question2;
    private Survey survey;
    private Answer answer1;
    private Answer answer2;


    @BeforeEach
    void setUp() {
        survey = new Survey();
        question1 = new Question();
        question2 = new Question();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        survey.addQuestion(question1);
        survey.addQuestion((question2));
        answer1 = new Answer();
        answer1.setQuestion(question1);
        question1.addAnswer(answer1);

        answer2 = new Answer();
        answer2.setQuestion(question2);
        question2.addAnswer(answer2);

        survey = surveyRepository.save(survey);
//        question1 = questionRepository.save(question1);
//        question2 = questionRepository.save(question2);

//        answer1 = answerRepository.save(answer1);
//        answer2 = answerRepository.save(answer2);

    }

    @AfterEach
    void tearDown() {
        surveyRepository.deleteAll();
    }

    @Test
    void findAllBySurveyId() {
        assertEquals(2, questionRepository.findAllBySurveyId(survey.getId()).size());
    }

    @Test
    void findById() {
        assertNotNull(questionRepository.findById(question1.getId()));
        assertNotNull(questionRepository.findById(question2.getId()));
    }

    @Test
    void deleteById() {

        assertEquals(survey.getQuestions(), Set.of(question1, question2));

        questionRepository.deleteById(question1.getId());

        assertEquals(1, questionRepository.findAllBySurveyId(survey.getId()).size(), "Should be a length of 1");
        assertNull(questionRepository.findById(question1.getId()));
        assertEquals(0, answerRepository.findAllByQuestionId(question1.getId()).size());
        assertEquals(1, answerRepository.findAllByQuestionId(question2.getId()).size());
    }

    @Test
    @Disabled
    void deleteAll() {
    }


}