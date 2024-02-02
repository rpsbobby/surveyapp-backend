package com.surver.app.backend.dto.surveydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDtoPost {

    @JsonProperty
    private String answer;
    @JsonProperty("questionId")
    private Long questionId;
}
