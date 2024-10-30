package com.practice.swd392_demo.repository.question;

import com.practice.swd392_demo.models.Question;

import java.util.List;

public interface IQuestionRepository {
    public void InsertQuestion(Question question);
    public void UpdateQuestion(Question question);
    public void DeleteQuestion(int id);
    public List<Question> getList();
    public Question GetQuestion(int id);
}
