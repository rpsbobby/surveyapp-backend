package com.surver.app.backend.mappers;

import com.surver.app.backend.dto.*;
import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.entity.Question;
import com.surver.app.backend.entity.Survey;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SurveyAppDtoMapperImpl implements SurveyAppDtoMapper {

    @Override
    public SurveysDto surveysToListDtoSlim(List<Survey> surveys) {
        if (surveys == null) return null;
        List<SurveySlimDto> temp = new ArrayList<>(surveys.size());
        for (Survey s : surveys) {
            temp.add(surveyToDtoSlim(s));
        }
        SurveysDto surveysDto = new SurveysDto();
        surveysDto.setSurveys(temp);
        return surveysDto;
    }

    @Override
    public SurveySlimDto surveyToDtoSlim(Survey survey) {
        if (survey == null) return null;
        SurveySlimDto temp = new SurveySlimDto();
        temp.setId(survey.getId());
        temp.setName(survey.getTitle());
        Set<QuestionSlimDto> questionSlimDto = new HashSet<>(survey.getQuestions().size());
        for (Question q : survey.getQuestions()) {
            questionSlimDto.add(questionToDtoSlim(q));
        }
        temp.setQuestions(questionSlimDto);
        return temp;
    }


    @Override
    public QuestionSlimDto questionToDtoSlim(Question question) {
        if (question == null) return null;
        QuestionSlimDto temp = new QuestionSlimDto();
        temp.setId(question.getId());
        temp.setQuestion(question.getQuestion());
        return temp;
    }

    @Override
    public Survey mapSurveyDtoToSurvey(PostSurveyDto surveyDto) {
        if (surveyDto == null) return null;
        Survey temp = new Survey();

        temp.setTitle(surveyDto.getTitle());
        Set<Question> questions = new HashSet<>();
        for (QuestionDto q : surveyDto.getQuestions()) {
            Question question = new Question();
            question.setQuestion(q.getQuestion());
            question.setSurvey(temp);
            question.setAnswers(new ArrayList<>(0));
            questions.add(question);
        }
        temp.setQuestions(questions);
        return temp;
    }

//    private Survey mapSurveyDtoToSurvey(SurveyDto surveyDto) {
//        Survey temp = new Survey();
//        temp.setId(surveyDto.getId());
//        temp.setName();
//
//    }

    @Override
    public Set<Question> mapQuestionDtoListToQuestionList(Set<QuestionDto> questions, Survey survey) {
        if (questions == null) return Collections.emptySet();
        Set<Question> temp = new HashSet<>(questions.size());
        for (QuestionDto questionDto : questions) {
            temp.add(mapQuestionDtoToQuestion(questionDto,survey));
        }
        return temp;
    }

    private Question mapQuestionDtoToQuestion(QuestionDto q,Survey survey) {
        Question temp = new Question();
        temp.setId(q.getId());
        temp.setQuestion(q.getQuestion());
        temp.setAnswers(mapAnswerDtoListToAnswerList(q.getAnswers(), temp));
        temp.setSurvey(survey);
        return temp;
    }

    private List<Answer> mapAnswerDtoListToAnswerList(List<AnswerDto> answers, Question question) {
        if (answers == null) return Collections.emptyList();
        List<Answer> temp = new ArrayList<>(answers.size());
        for (AnswerDto answerDto : answers) {
            temp.add(mapAnswerDtoToAnswer(answerDto,question));
        }
        return temp;
    }

    private Answer mapAnswerDtoToAnswer(AnswerDto answerDto, Question question) {
        Answer temp = new Answer();
        temp.setId(answerDto.getId());
        temp.setAnswer(answerDto.getAnswer());
        temp.setQuestion(question);
        return temp;
    }


    @Override
    public SurveyDto surveyToDto(Survey survey) {
        if (survey == null) return null;
        SurveyDto temp = new SurveyDto();
        temp.setId(survey.getId());
        temp.setTitle(survey.getTitle());
        Set<Question> questions = survey.getQuestions();
        temp.setQuestions(questionSetToDto(survey.getQuestions(), temp.getId()));
        return temp;

    }


    @Override
    public Set<QuestionDto> questionSetToDto(Set<Question> questions, Long surveyId) {
        if (questions == null) return Collections.emptySet();
        Set<QuestionDto> questionsDto = new HashSet<>(questions.size());
        for (Question q : questions) {
            questionsDto.add(questionToDto(q, surveyId));
        }
        return questionsDto;
    }

    @Override
    public QuestionDto questionToDto(Question question, Long surveyId) {
        if (question == null) return null;
        QuestionDto temp = new QuestionDto();
        temp.setId(question.getId());
        temp.setQuestion(question.getQuestion());
        temp.setSurveyId(surveyId);
        temp.setAnswers(answerListToDto(question.getAnswers(), temp.getId()));
        return temp;
    }

    @Override
    public List<AnswerDto> answerListToDto(List<Answer> answers, Long questionId) {
        List<AnswerDto> answersDto = new ArrayList<>(answers.size());
        for (Answer a : answers) {
            answersDto.add(answerToDto(a, questionId));
        }
        return answersDto;
    }

    @Override
    public AnswerDto answerToDto(Answer answer, Long questionId) {
        AnswerDto temp = new AnswerDto();
        temp.setId(answer.getId());
        temp.setAnswer(answer.getAnswer());
        temp.setQuestionId(questionId);
        return temp;
    }
}
