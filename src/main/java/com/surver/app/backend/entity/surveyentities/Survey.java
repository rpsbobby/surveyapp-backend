package com.surver.app.backend.entity.surveyentities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "creator")
    private String creator;
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Question> questions;

    public void addQuestion(Question question) {
        if (questions == null) questions = new HashSet<>();
        questions.add(question);
    }


    public void removeQuestion(Question question) {
        if (question == null) throw new IllegalArgumentException("Question must be not null");

        questions.remove(question);
    }
}
