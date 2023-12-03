package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.repository.surveyrepositories.SurveyRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {


    @Mock
    private SurveyRepository mock;


    private SurveyService service;
    private AutoCloseable autoCloseable;

    private Survey survey;
    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        survey = new Survey();
        survey.setId(1L);
        service = new SurveyServiceImpl(mock);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAll() {
        List<Survey> list = List.of(survey);
        when(mock.findAll()).thenReturn(list);

        Assertions.assertEquals(list, service.findAll());

        verify(mock,  Mockito.atLeastOnce()).findAll();

    }

    @Test
    void findById() {
        // valid id
        Mockito.when(mock.findById(survey.getId())).thenReturn(Optional.of(survey));
        Assertions.assertEquals(survey, service.findById(survey.getId()));
        // invalid id
        Mockito.when(mock.findById(10L)).thenReturn(Optional.empty());
        Assertions.assertNull(service.findById(10L));

        Mockito.verify(mock, Mockito.atMost(2)).findById(survey.getId());
    }

    @Test
    void save() {

        service.save(survey);
        Mockito.verify(mock, Mockito.atLeastOnce()).save(survey);


    }

    @Test
    @Disabled
    void updateSurvey() {
    }

    @Test
    void deleteSurveyById() {
        service.deleteSurveyById(survey.getId());
        Mockito.verify(mock, Mockito.atLeastOnce()).deleteById(survey.getId());
    }
}