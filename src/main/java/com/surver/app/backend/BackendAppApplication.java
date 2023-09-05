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


    public static void main(String[] args) {
        SpringApplication.run(BackendAppApplication.class, args);
    }


}
