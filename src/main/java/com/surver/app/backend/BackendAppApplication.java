package com.surver.app.backend;

import com.surver.app.backend.services.surveyservices.QuestionService;
import com.surver.app.backend.services.surveyservices.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BackendAppApplication {

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${sql.insert.into.survey.1}")
    private String addSurvey1;
    @Value("${sql.insert.into.question.1}")
    private String addQuestion1;
    @Value("${sql.insert.into.answer.1}")
    private String addAnswer1;
    @Value("${sql.insert.into.question.2}")
    private String addQuestion2;
    @Value("${sql.insert.into.answer.2}")
    private String addAnswer2;

    @Autowired
    public SurveyService surveyService;
    @Autowired
    public QuestionService questionService;


    public static void main(String[] args) {
        SpringApplication.run(BackendAppApplication.class, args);
    }


//    @Override
//    public void run(String... arg0) throws Exception {
//
//        //question 1
//        Question q1 = new Question("How Are You?");
//        List<Answer> a1 = new ArrayList<>();
//        Answer answer1 = new Answer(q1, "I'm Good");
//        answer1.setQuestion(q1);
//        a1.add(answer1);
//        q1.setAnswers(a1);
//        //question 2
//        Question q2 = new Question("What is Your Name?");
//        List<Answer> a2 = new ArrayList<>();
//        Answer answer2 = new Answer(q1, "John Doe");
//        a2.add(answer2);
//        answer2.setQuestion(q2);
//        q2.setAnswers(a2);
//        //question 3
//        Question q3 = new Question("Where Do you Live?");
//        List<Answer> a3 = new ArrayList<>();
//        Answer answer3 = new Answer(q1, "Middle of Nowhere");
//        a3.add(answer3);
//        answer3.setQuestion(q3);
//        q3.setAnswers(a3);
//        //survey
//        Survey survey = new Survey();
//        survey.setName("First");
//        survey.addQuestion(q1);
//        q1.setSurvey(survey);
//        survey.addQuestion(q2);
//        q2.setSurvey(survey);
//        survey.addQuestion(q3);
//        q3.setSurvey(survey);
//
//
//        surveyService.addSurvey(survey);
////        questionService.update(q1);
////        questionService.update(q2);
////        questionService.update(question3);
//
//
//        System.out.println(surveyService.findAll().size());
//
//    }


}
