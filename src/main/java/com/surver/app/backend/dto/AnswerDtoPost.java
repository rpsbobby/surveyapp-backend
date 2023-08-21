package com.surver.app.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDtoPost {

    @JsonProperty
    private String answer;
    @JsonProperty("questionId") // question or question id
    private Long questionId;
}
