package com.surver.app.backend.dto.surveydto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty
    private String answer;
    @JsonProperty("questionId") // question or question id
    private Long questionId;
}
