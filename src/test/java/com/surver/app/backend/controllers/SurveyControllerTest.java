package com.surver.app.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.surver.app.backend.dto.PostSurveyDto;
import com.surver.app.backend.dto.SurveyDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SurveyControllerTest {

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

    @Value("${sql.delete.survey}")
    private String deleteSurvey;
    @Value("${sql.delete.question}")
    private String deleteQuestion;
    @Value("${sql.delete.answer}")
    private String deleteAnswer;

    @BeforeEach
    void beforeEach() {
        jdbc.execute(addSurvey1);
        jdbc.execute(addQuestion1);
        jdbc.execute(addAnswer1);
        jdbc.execute(addQuestion2);
        jdbc.execute(addAnswer2);
    }

    @AfterEach
    void afterEach() {
        jdbc.execute(deleteAnswer);
        jdbc.execute(deleteQuestion);
        jdbc.execute(deleteSurvey);
    }


    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(1)
    void findAll() throws Exception {
        mockMvc.perform(get("/survey/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.surveys", hasSize(1)));
    }

    @Test
    @Order(2)
    void findById() throws Exception {
        mockMvc.perform(get("/survey/findById/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("First")));
        mockMvc.perform(get("/survey/findById/-1"))
                .andExpect(status().is4xxClientError());
//

    }


    @Test
    @Order(3)
    void addNewSurvey() throws Exception {
        PostSurveyDto temp = new PostSurveyDto();
        temp.setTitle("Second");
        temp.setQuestions(Collections.emptySet());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(temp );
        mockMvc.perform(post("/survey/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/survey/findById/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Second")));

    }

    @Test
    @Order(4)
    void updateSurvey() throws Exception {
        SurveyDto temp = new SurveyDto();
        temp.setId(10L);
        temp.setTitle("Test4");
        temp.setQuestions(Collections.emptySet());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(temp );
        mockMvc.perform(post("/survey/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/survey/findById/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Test4")));
    }

    @Test
    @Order(99)
    void deleteQuestion() throws Exception {
        mockMvc.perform(delete("/survey/delete-question/20"))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/survey/delete-question/20"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @Order(100)
    void deleteSurveyById() throws Exception {
        mockMvc.perform(delete("/survey/delete/10"))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/survey/delete/-1"))
                .andExpect(status().is4xxClientError());
    }


}