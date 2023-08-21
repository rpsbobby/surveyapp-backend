package com.surver.app.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("survey_id")
    private Long surveyId;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answers")
    private List<AnswerDto> answers;


}
