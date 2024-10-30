package com.practice.swd392_demo.repository.answer;

import com.practice.swd392_demo.models.Answer;
import com.practice.swd392_demo.models.Question;

import java.util.List;

public interface IAnswerRepository {
    public void InsertAnswer(Answer question);
    public void UpdateAnswer(Answer question);
    public void DeleteAnswer(int id);
    public List<Answer> getList();
    public Answer GetAnswer(int id);
}
