package com.surver.app.backend.controllers;

import com.surver.app.backend.dto.surveydto.AnswerDtoPost;
import com.surver.app.backend.dto.surveydto.SurveyDto;
import com.surver.app.backend.dto.surveydto.SurveyDtoPost;
import com.surver.app.backend.dto.surveydto.SurveysDto;
import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.entity.surveyentities.Question;
import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.mappers.surveymappers.SurveyAppDtoMapper;
import com.surver.app.backend.services.auth.JwtService;
import com.surver.app.backend.services.surveyservices.AnswerService;
import com.surver.app.backend.services.surveyservices.QuestionService;
import com.surver.app.backend.services.surveyservices.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.headers.HeadersSecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyAppDtoMapper mapper;
    private final QuestionService questionService;
    private final SurveyService surveyService;
    private final AnswerService answerService;
    private final JwtService jwtService;

    @GetMapping("/findAll")
    public ResponseEntity<SurveysDto> findAll() {
        return new ResponseEntity<>(mapper.mapSurveysToListDtoSlim(surveyService.findAll()), HttpStatus.OK);
    }


    @GetMapping("/findById/{surveyId}")
    public ResponseEntity<SurveyDto> findById(@PathVariable Long surveyId) {
        if (surveyService.findById(surveyId) == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.mapSurveyToDto(surveyService.findById(surveyId)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{surveyId}")
    public ResponseEntity<String> deleteSurveyById(@RequestHeader Map<String, String> headers, @PathVariable Long surveyId) {
        Survey temp = surveyService.findById(surveyId);
        if (temp == null) return new ResponseEntity<>("Survey Not Found", HttpStatus.NOT_FOUND);
        String token =  extractToken(headers.get("authorization"));
        if(!validateSurveyOwnership(token,temp.getCreator()))
            return new ResponseEntity<>("Not Authorised", HttpStatus.UNAUTHORIZED);
        surveyService.deleteSurveyById(surveyId);
        return new ResponseEntity<>("Survey successfully deleted", HttpStatus.OK);
    }



    @DeleteMapping("/delete-question/{questionId}/survey/{surveyId}")
    public ResponseEntity<String> deleteQuestion(@RequestHeader Map<String, String> headers,
                                                 @PathVariable Long questionId,
                                                 @PathVariable Long surveyId) {
        if (questionService.findById(questionId) == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        questionService.deleteById(questionId);
        return new ResponseEntity<>("Completed", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewSurvey(@RequestHeader Map<String, String> headers,
                                               @RequestBody SurveyDtoPost survey) {
        String token = extractToken(headers.get("authorization"));
        String creator = survey.getCreator();

        if(!validateSurveyOwnership(token,creator))
            return new ResponseEntity<>("Not Authorised", HttpStatus.UNAUTHORIZED);

        surveyService.save(mapper.mapSurveyDtoPostToSurvey(survey));
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }


    @PostMapping("/submit/{surveyId}")
    public void submit(@PathVariable Long surveyId, @RequestBody List<AnswerDtoPost> answers) {
        Survey survey = surveyService.findById(surveyId);
        List<Answer> temp = mapper.mapAnswerDtoPostListToAnswer(answers, survey);
        answerService.addAllAnswers(temp);
    }

    private boolean validateSurveyOwnership(String token, String owner) {
        return Objects.equals(owner, jwtService.extractUsername(token));
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

}
