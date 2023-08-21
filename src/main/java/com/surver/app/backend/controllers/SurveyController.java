package com.surver.app.backend.controllers;

import com.surver.app.backend.dto.AnswerDtoPost;
import com.surver.app.backend.dto.SurveyDto;
import com.surver.app.backend.dto.SurveyDtoPost;
import com.surver.app.backend.dto.SurveysDto;
import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.entity.Question;
import com.surver.app.backend.entity.Survey;
import com.surver.app.backend.mappers.SurveyAppDtoMapper;
import com.surver.app.backend.services.AnswerService;
import com.surver.app.backend.services.QuestionService;
import com.surver.app.backend.services.SurveyService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin()
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private SurveyAppDtoMapper mapper;
    private QuestionService questionService;
    private SurveyService surveyService;
    private AnswerService answerService;

    public SurveyController(SurveyAppDtoMapper mapper, QuestionService questionService, SurveyService surveyService, AnswerService answerService) {
        this.mapper = mapper;
        this.questionService = questionService;
        this.surveyService = surveyService;
        this.answerService = answerService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<SurveysDto> findAll() {
        return new ResponseEntity<>(mapper.mapSurveysToListDtoSlim(surveyService.findAll()), HttpStatus.OK);
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<SurveyDto> findById(@PathVariable Long id) {
        if (surveyService.findById(id) == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.mapSurveyToDto(surveyService.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSurveyById(@PathVariable Long id) {
        if (surveyService.findById(id) == null) return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        surveyService.deleteSurveyById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @DeleteMapping("/delete-question/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        if (questionService.findById(id) == null) return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        questionService.deleteById(id);
        return new ResponseEntity<>("Completed", HttpStatus.OK);
    }

    @PostMapping("/add")
    public void addNewSurvey(@RequestBody SurveyDtoPost survey) {
        surveyService.addSurvey(mapper.mapSurveyDtoPostToSurvey(survey));
    }

    @PostMapping("/update")
    public void updateSurvey(@RequestBody SurveyDto survey) {
        surveyService.updateSurvey(mapper.mapSurveyDtoToSurvey(survey));
    }

    @PostMapping("/{surveyId}/add-answers")
    public void addAnswers(@RequestBody List<AnswerDtoPost> answers, @PathVariable Long surveyId) {
        Survey survey = surveyService.findById(surveyId);
        List<Answer> temp = mapper.mapAnswerDtoPostListToAnswer(answers, survey);
        answerService.addAllAnswers(temp);
    }

//        @PostConstruct
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
        survey.setTitle("First");
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
