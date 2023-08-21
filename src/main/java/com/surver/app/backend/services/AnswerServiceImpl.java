package com.surver.app.backend.services;

import com.surver.app.backend.entity.Answer;
import com.surver.app.backend.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;


    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> findAllAnswersByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    @Override
    @Transactional
    public void addAnswer(Answer answer) {
        answerRepository.save(answer);

    }

    @Override
    @Transactional
    public void addAllAnswers(List<Answer> answers) {
        if (answers != null) answerRepository.saveAll(answers);
    }

    @Override
    @Transactional
    public void updateAnswer(Answer answer) {
        if (answer != null) answerRepository.save(answer);

    }

    @Override
    @Transactional
    public void deleteAnswerById(Long answerId) {
        answerRepository.deleteById(answerId);
    }

    @Transactional
    @Override
    public void deleteAllAnswersByQuestionId(Long questionId) {
        answerRepository.deleteAllByQuestionId(questionId);
    }
}
