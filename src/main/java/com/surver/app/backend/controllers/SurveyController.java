package com.surver.app.backend.controllers;

import com.surver.app.backend.dto.surveydto.AnswerDtoPost;
import com.surver.app.backend.dto.surveydto.SurveyDto;
import com.surver.app.backend.dto.surveydto.SurveyDtoPost;
import com.surver.app.backend.dto.surveydto.SurveysDto;
import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.mappers.surveymappers.SurveyAppDtoMapper;
import com.surver.app.backend.services.surveyservices.AnswerService;
import com.surver.app.backend.services.surveyservices.QuestionService;
import com.surver.app.backend.services.surveyservices.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/api/survey")
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
        questionService.deleteAllBySurveyId(survey.getId());
        surveyService.updateSurvey(mapper.mapSurveyDtoToSurvey(survey));
    }

    @PostMapping("/{surveyId}/add-answers")
    public void addAnswers(@PathVariable Long surveyId, @RequestBody List<AnswerDtoPost> answers) {
        Survey survey = surveyService.findById(surveyId);
        List<Answer> temp = mapper.mapAnswerDtoPostListToAnswer(answers, survey);
        answerService.addAllAnswers(temp);
    }


}
