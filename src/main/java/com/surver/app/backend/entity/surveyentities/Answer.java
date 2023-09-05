package com.surver.app.backend.entity.surveyentities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;
    @Column(name = "answer")
    private String answer;

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
