package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.repository.surveyrepositories.AnswerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    @Mock
    private AnswerRepository mock;


    private AnswerService service;
    private AutoCloseable autoCloseable;

    private Long questionId;
    private Long answerId;


    @BeforeEach
    public void setup() {
        answerId = 2L;
        questionId = 1L;
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new AnswerServiceImpl(mock);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAllAnswersByQuestionId() {

        service.findAllAnswersByQuestionId(questionId);
        Mockito.verify(mock, Mockito.atLeast(1)).findAllByQuestionId(questionId);

    }


    @Test
    void testFindAllAnswersByQuestionId() {
        service.findAllAnswersByQuestionId(questionId);
        Mockito.verify(mock, Mockito.atLeastOnce()).findAllByQuestionId(questionId);
    }

    @Test
    void testAddAnswer() {
        Answer newAnswer = new Answer();
        service.addAnswer(newAnswer);
        Mockito.verify(mock, Mockito.atLeastOnce()).save(newAnswer);
    }

    @Test
    void addAllAnswers() {
        List<Answer> answers = List.of(new Answer(), new Answer());
        service.addAllAnswers(answers);
        Mockito.verify(mock, Mockito.atLeastOnce()).saveAll(answers);
    }

    @Test
    @Disabled
    void updateAnswer() {
    }

    @Test
    void deleteAnswerById() {
        service.deleteAnswerById(answerId);
        Mockito.verify(mock, Mockito.atLeastOnce()).deleteById(answerId);
    }

    @Test
    void deleteAllAnswersByQuestionId() {
        service.deleteAllAnswersByQuestionId(questionId);
        Mockito.verify(mock, Mockito.atLeastOnce()).deleteAllByQuestionId(questionId);
    }
}
