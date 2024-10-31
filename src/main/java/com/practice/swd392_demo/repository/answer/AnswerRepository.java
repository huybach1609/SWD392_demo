package com.practice.swd392_demo.repository.answer;

import com.practice.swd392_demo.DAL.AccountDAO;
import com.practice.swd392_demo.DAL.AnswerDAO;
import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.Answer;
import com.practice.swd392_demo.models.Question;
import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.question.QuestionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class AnswerRepository implements IAnswerRepository {

    public AnswerRepository() {
    }

    @Override
    public void InsertAnswer(Answer ans) {
        AnswerDAO.ins.saveAnswer(ans);
    }

    @Override
    public void UpdateAnswer(Answer ans) {
        AnswerDAO.ins.updateAnswer(ans);
    }

    @Override
    public void DeleteAnswer(int id) {
        AnswerDAO.ins.deleteAnswer(id);
    }

    @Override
    public List<Answer> getList() {
        return AnswerDAO.ins.getList();
    }

    @Override
    public Answer GetAnswer(int id) {
        return AnswerDAO.ins.getByID(id);
    }

}