package com.surver.app.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class QuestionSlimDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("question")
    private String question;
}
