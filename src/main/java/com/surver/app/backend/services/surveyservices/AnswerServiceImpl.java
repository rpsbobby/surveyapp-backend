package com.surver.app.backend.services.surveyservices;

import com.surver.app.backend.entity.surveyentities.Answer;
import com.surver.app.backend.repository.surveyrepositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public List<Answer> findAllAnswersByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    @Override
    @Transactional
    public Answer addAnswer(Answer answer) {
        return answerRepository.save(answer);

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
