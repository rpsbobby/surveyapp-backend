package com.surver.app.backend.dto.surveydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class SurveyDtoPost {
    @JsonProperty("title")
    private String title;
    @JsonProperty("questions")
    private Set<QuestionDto> questions;
    @JsonProperty("creator")
    private String creator;
}
