package com.surver.app.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class PostSurveyDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("questions")
    private Set<QuestionDto> questions;
}
