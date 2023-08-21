package com.surver.app.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/")).andReturn();
    }

    @Test
    void findById() {
    }

    @Test
    void deleteSurveyById() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void addNewSurvey() {
    }

    @Test
    void updateSurvey() {
    }

    @Test
    void setUp() {
    }
}