package com.surver.app.backend.services;

import com.surver.app.backend.repository.QuestionRepository;
import com.surver.app.backend.entity.Question;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionDao;

    private EntityManager manager;

    public QuestionServiceImpl(QuestionRepository questionDao, EntityManager manager) {
        this.questionDao = questionDao;
        this.manager = manager;
    }

    @Override
    public List<Question> findAllBySurveyId(long id) {
        List<Question> temp = new ArrayList<>();
        questionDao.findAll().forEach(temp::add);
        return temp;
    }

    @Override
    public Question findById(long id) {
        return questionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(long id) {

        Question question = findById(id);
        questionDao.delete(question);
    }

    @Override
    @Transactional
    public void update(Question question) {
        questionDao.save(question);
    }
}