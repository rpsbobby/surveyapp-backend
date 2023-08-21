package com.surver.app.backend.controllers;

import com.surver.app.backend.dto.PostSurveyDto;
import com.surver.app.backend.dto.SurveyDto;
import com.surver.app.backend.dto.SurveysDto;
import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.entity.Question;
import com.surver.app.backend.entity.Survey;
import com.surver.app.backend.mappers.SurveyAppDtoMapper;
import com.surver.app.backend.services.QuestionService;
import com.surver.app.backend.services.SurveyService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private SurveyAppDtoMapper mapper;
    private QuestionService questionService;
    private SurveyService surveyService;

    public SurveyController(SurveyAppDtoMapper mapper, QuestionService questionService, SurveyService surveyService) {
        this.mapper = mapper;
        this.questionService = questionService;
        this.surveyService = surveyService;
    }


    @GetMapping("/findAll")
    public ResponseEntity<SurveysDto> findAll() {
        return new ResponseEntity<>(mapper.surveysToListDtoSlim(surveyService.findAll()), HttpStatus.OK);
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<SurveyDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(mapper.surveyToDto(surveyService.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSurveyById(@PathVariable Long id) {
        surveyService.deleteSurveyById(id);
    }


    @DeleteMapping("/delete-question/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteById(id);
    }

    @PostMapping("/add")
    public void addNewSurvey(@RequestBody PostSurveyDto survey) {
        surveyService.addSurvey(mapper.mapSurveyDtoToSurvey(survey));
    }

    @PostMapping("/update")
    public void updateSurvey(@RequestBody SurveyDto survey) {
        Survey temp = surveyService.findById(survey.getId());
        temp.setName(survey.getName());
        Set<Question> questions = mapper.mapQuestionDtoListToQuestionList(survey.getQuestions(), temp);
        temp.setQuestions(questions);
        surveyService.updateSurvey(temp);
    }

    @PostConstruct
    public void setUp() {
        Question q1 = new Question("How Are You?");
        List<Answer> a1 = new ArrayList<>();
        Answer answer1 = new Answer(q1, "I'm Good");
        answer1.setQuestion(q1);
        a1.add(answer1);
        q1.setAnswers(a1);
        //question 2
        Question q2 = new Question("What is Your Name?");
        List<Answer> a2 = new ArrayList<>();
        Answer answer2 = new Answer(q1, "John Doe");
        a2.add(answer2);
        answer2.setQuestion(q2);
        q2.setAnswers(a2);
        //question 3
        Question q3 = new Question("Where Do you Live?");
        List<Answer> a3 = new ArrayList<>();
        Answer answer3 = new Answer(q1, "Middle of Nowhere");
        a3.add(answer3);
        answer3.setQuestion(q3);
        q3.setAnswers(a3);
        //survey
        Survey survey = new Survey();
        survey.setName("First");
        survey.addQuestion(q1);
        q1.setSurvey(survey);
        survey.addQuestion(q2);
        q2.setSurvey(survey);
        survey.addQuestion(q3);
        q3.setSurvey(survey);


        surveyService.addSurvey(survey);
        System.out.println("Set up");
    }


}
