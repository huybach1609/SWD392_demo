package com.practice.swd392_demo.repository.question;

import com.practice.swd392_demo.DAL.QuestionDAO;
import com.practice.swd392_demo.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements IQuestionRepository {


    public QuestionRepository() {
    }


    @Override
    public void InsertQuestion(Question question) {
        QuestionDAO.ins.saveQuestion(question);
    }

    @Override
    public void UpdateQuestion(Question question) {
        QuestionDAO.ins.saveQuestion(question);
    }

    @Override
    public void DeleteQuestion(int id) {
        QuestionDAO.ins.deleteQuestion(id);
    }

    @Override
    public List<Question> getList() {
        return QuestionDAO.ins.getList();
    }

    @Override
    public Question GetQuestion(int id) {
        return QuestionDAO.ins.getByID(id);
    }
}