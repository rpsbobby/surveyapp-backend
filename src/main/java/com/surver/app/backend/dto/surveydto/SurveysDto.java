package com.surver.app.backend.dto.surveydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SurveysDto {

    @JsonProperty("surveys")
    private List<SurveySlimDto> surveys;
}
