package com.surver.app.backend.mappers.surveymappers;

import com.surver.app.backend.dto.surveydto.*;
import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.entity.surveyentities.Question;
import com.surver.app.backend.entity.surveyentities.Survey;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SurveyAppDtoMapperImpl implements SurveyAppDtoMapper {

    @Override
    public SurveysDto mapSurveysToListDtoSlim(List<Survey> surveys) {
        if (surveys == null) return null;
        List<SurveySlimDto> temp = new ArrayList<>(surveys.size());
        for (Survey s : surveys) {
            temp.add(mapSurveyToDtoSlim(s));
        }
        SurveysDto surveysDto = new SurveysDto();
        surveysDto.setSurveys(temp);
        return surveysDto;
    }

    @Override
    public SurveySlimDto mapSurveyToDtoSlim(Survey survey) {
        if (survey == null) return null;
        SurveySlimDto temp = new SurveySlimDto();
        temp.setId(survey.getId());
        temp.setName(survey.getTitle());
        Set<QuestionSlimDto> questionSlimDto = new HashSet<>(survey.getQuestions().size());
        for (Question q : survey.getQuestions()) {
            questionSlimDto.add(mapQuestionToDtoSlim(q));
        }
        temp.setQuestions(questionSlimDto);
        return temp;
    }


    @Override
    public QuestionSlimDto mapQuestionToDtoSlim(Question question) {
        if (question == null) return null;
        QuestionSlimDto temp = new QuestionSlimDto();
        temp.setId(question.getId());
        temp.setQuestion(question.getQuestion());
        return temp;
    }

    @Override
    public Survey mapSurveyDtoPostToSurvey(SurveyDtoPost surveyDto) {
        if (surveyDto == null) return null;
        Survey temp = new Survey();

        temp.setTitle(surveyDto.getTitle());
        temp.setCreator(surveyDto.getCreator());
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
        System.out.println(q.getId());
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
    public SurveyDto mapSurveyToDto(Survey survey) {
        if (survey == null) return null;
        SurveyDto temp = new SurveyDto();
        temp.setId(survey.getId());
        temp.setTitle(survey.getTitle());
        temp.setCreator(survey.getCreator());
        Set<Question> questions = survey.getQuestions();
        temp.setQuestions(mapQuestionSetToDto(survey.getQuestions(), temp.getId()));
        return temp;

    }


    @Override
    public Set<QuestionDto> mapQuestionSetToDto(Set<Question> questions, Long surveyId) {
        if (questions == null) return Collections.emptySet();
        Set<QuestionDto> questionsDto = new HashSet<>(questions.size());
        for (Question q : questions) {
            questionsDto.add(mapQuestionToDto(q, surveyId));
        }
        return questionsDto;
    }

    @Override
    public QuestionDto mapQuestionToDto(Question question, Long surveyId) {
        if (question == null) return null;
        QuestionDto temp = new QuestionDto();
        temp.setId(question.getId());
        temp.setQuestion(question.getQuestion());
        temp.setSurveyId(surveyId);
        temp.setAnswers(mapAnswerListToDto(question.getAnswers(), temp.getId()));
        return temp;
    }

    @Override
    public List<AnswerDto> mapAnswerListToDto(List<Answer> answers, Long questionId) {
        List<AnswerDto> answersDto = new ArrayList<>(answers.size());
        for (Answer a : answers) {
            answersDto.add(mapAnswerToDto(a, questionId));
        }
        return answersDto;
    }

    @Override
    public AnswerDto mapAnswerToDto(Answer answer, Long questionId) {
        AnswerDto temp = new AnswerDto();
        temp.setId(answer.getId());
        temp.setAnswer(answer.getAnswer());
        temp.setQuestionId(questionId);
        return temp;
    }

    @Override
    public List<Answer> mapAnswerDtoPostListToAnswer(List<AnswerDtoPost> answersDtoPost, Survey survey) {
        if(answersDtoPost == null || answersDtoPost.size() == 0) {
            return Collections.emptyList();
        }
        List<Question> questions = survey.getQuestions().stream().toList();
        List<Answer> answers = new ArrayList<>(answersDtoPost.size());
        for(AnswerDtoPost a: answersDtoPost) {
            Question question = null;
            for(Question q : questions) {
                if(Objects.equals(q.getId(), a.getQuestionId())) question = q;
            }
            assert (question != null);
            answers.add(answersDtoPostToAnswer(a,question));
        }

        return answers;
    }

    private Answer answersDtoPostToAnswer(AnswerDtoPost answerDtoPost,Question question) {
        Answer temp = new Answer();
        temp.setAnswer(answerDtoPost.getAnswer());
        temp.setQuestion(question);
        return temp;
    }

    @Override
    public Survey mapSurveyDtoToSurvey(SurveyDto survey) {
        Survey temp = new Survey();
        temp.setId(survey.getId());
        temp.setTitle(survey.getTitle());
        temp.setCreator(survey.getCreator());
        temp.setQuestions(mapQuestionDtoListToQuestionList(survey.getQuestions(),temp));
        return temp;
    }
}
