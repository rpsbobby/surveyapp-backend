package com.surver.app.backend.mappers;

import com.surver.app.backend.dto.*;
import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.entity.Question;
import com.surver.app.backend.entity.Survey;

import java.util.List;
import java.util.Set;


public interface SurveyAppDtoMapper {

    SurveyDto mapSurveyToDto(Survey survey);

    SurveySlimDto mapSurveyToDtoSlim(Survey survey);

    QuestionDto mapQuestionToDto(Question question, Long surveyId);

    Set<QuestionDto> mapQuestionSetToDto(Set<Question> questions, Long surveyId);

    QuestionSlimDto mapQuestionToDtoSlim(Question question);

    Survey mapSurveyDtoPostToSurvey(SurveyDtoPost surveyDto);

    Set<Question> mapQuestionDtoListToQuestionList(Set<QuestionDto> questions,Survey survey);

    AnswerDto mapAnswerToDto(Answer answer, Long questionId);

    List<AnswerDto> mapAnswerListToDto(List<Answer> answers, Long questionId);

    SurveysDto mapSurveysToListDtoSlim(List<Survey> surveys);

    List<Answer> mapAnswerDtoPostListToAnswer(List<AnswerDtoPost> answers, Survey survey);

    Survey mapSurveyDtoToSurvey(SurveyDto survey);


}
