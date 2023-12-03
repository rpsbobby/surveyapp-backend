package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.repository.surveyrepositories.QuestionRepository;
import com.surver.app.backend.repository.surveyrepositories.SurveyRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository mockRepository;
    @Mock
    private SurveyRepository surveyServiceMock;

    private QuestionService service;
    private AutoCloseable autoCloseable;


    private Long surveyId;
    private Long questionId;


    @BeforeEach
    void setUp() {
        surveyId = 10L;
        questionId = 2L;
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new QuestionServiceImpl(mockRepository, surveyServiceMock);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAllBySurveyId() {

        service.findAllBySurveyId(surveyId);
        Mockito.verify(mockRepository, Mockito.atLeastOnce()).findAllBySurveyId(surveyId);


    }

    @Test
    void findById() {

//        Assertions.assertThrows(NullPointerException.class,() -> service.findById(questionId));
        service.findById(questionId);

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).findById(questionId);
    }

    @Test
    void deleteById() {
        Assertions.assertThrows(NullPointerException.class,() -> service.deleteById(questionId));
        Mockito.verify(mockRepository, Mockito.atLeastOnce()).findById(questionId);
    }

    @Test
    @Disabled
    void deleteAll() {
    }
}