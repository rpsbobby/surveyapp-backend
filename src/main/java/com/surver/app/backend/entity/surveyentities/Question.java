package com.surver.app.backend.entity.surveyentities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questions")

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "survey_id")
    private Survey survey;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;
    @Column(name = "question")
    private String question;

    public Question(String question) {
        this.question = question;
    }

    public void addAnswer(Answer answer) {
        if(answers == null) answers = new ArrayList<>();
        answers.add(answer);
    }
}
