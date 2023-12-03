package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Question;
import com.surver.app.backend.entity.surveyentities.Survey;
import com.surver.app.backend.repository.surveyrepositories.QuestionRepository;
import com.surver.app.backend.repository.surveyrepositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {


    private  final QuestionRepository questionRepository;

    private final SurveyRepository surveyRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }


    @Override
    public List<Question> findAllBySurveyId(long id) {
        return questionRepository.findAllBySurveyId(id);
    }

    @Override
    public Question findById(long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(long id) {

//        System.out.println("Delete by id called");
//        // todo remove survey link

        Question question = questionRepository.findById(id).orElse(null);
        if(question == null) throw  new NullPointerException("Question not found");

        Survey survey = surveyRepository.findById(question.getSurvey().getId()).orElse(null);
        if(survey == null) throw  new NullPointerException("Survey not found");


        survey.removeQuestion(question);
        question.setSurvey(null);

        surveyRepository.save(survey);


    }

    @Transactional
    @Override
    public void deleteAll(List<Question> questions) {
        if (questions == null) throw new IllegalArgumentException("Questions must be not null");

        questions.forEach(question -> question.setSurvey(null));

        questionRepository.deleteAll(questions);
    }


    // todo
    // delete
    @Override
    @Transactional
    public void update(Question question) {
//        questionRepository.save(question);
    }
}
