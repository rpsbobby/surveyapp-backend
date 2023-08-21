package com.surver.app.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
public class SurveySlimDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("questions")
    private Set<QuestionSlimDto> questions;
}
