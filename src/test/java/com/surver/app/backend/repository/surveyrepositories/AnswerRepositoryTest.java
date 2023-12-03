package com.surver.app.backend.repository.surveyrepositories;

import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.entity.surveyentities.Question;
import com.surver.app.backend.entity.surveyentities.Survey;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class AnswerRepositoryTest {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    private Question question;
    private Survey survey;
    private Answer answer1;
    private Answer answer2;


    @BeforeEach
    void setUp() {
        survey = new Survey();
        question = new Question();
        question.setSurvey(survey);
        survey.addQuestion(question);

        answer1 = new Answer();
        answer1.setQuestion(question);
        question.addAnswer(answer1);

        answer2 = new Answer();
        answer2.setQuestion(question);
        question.addAnswer(answer2);

        survey = surveyRepository.save(survey);
        question = questionRepository.save(question);

        answer1 = answerRepository.save(answer1);
        answer2 = answerRepository.save(answer2);

    }

    @AfterEach
    void tearDown() {
        surveyRepository.deleteAll();
    }

    @Test
    void findAllByQuestionId() {
        System.out.println(question.getId());
        assertNotNull(answerRepository.findAllByQuestionId(question.getId()));
        assertEquals(2, answerRepository.findAllByQuestionId(question.getId()).size());
    }

    @Test
    //todo
    void deleteAllByQuestionId() {
        Question q = questionRepository.findById(question.getId()).orElse(null);
        q.setAnswers(null);
//        questionRepository.save(q);

        answerRepository.deleteAllByQuestionId(question.getId());
        assertEquals(0, answerRepository.findAllByQuestionId(question.getId()).size());
    }

}