package com.surver.app.backend.mappers;

import com.surver.app.backend.dto.*;
import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.entity.Question;
import com.surver.app.backend.entity.Survey;

import java.util.List;
import java.util.Set;


public interface SurveyAppDtoMapper {

    SurveyDto surveyToDto(Survey survey);

    SurveySlimDto surveyToDtoSlim(Survey survey);

    QuestionDto questionToDto(Question question, Long surveyId);

    Set<QuestionDto> questionSetToDto(Set<Question> questions, Long surveyId);

    QuestionSlimDto questionToDtoSlim(Question question);

    Survey mapSurveyDtoToSurvey(PostSurveyDto surveyDto);

    Set<Question> mapQuestionDtoListToQuestionList(Set<QuestionDto> questions,Survey survey);

    AnswerDto answerToDto(Answer answer, Long questionId);

    List<AnswerDto> answerListToDto(List<Answer> answers, Long questionId);

    SurveysDto surveysToListDtoSlim(List<Survey> surveys);


}
