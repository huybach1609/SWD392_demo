package com.practice.swd392_demo.DAL;

import com.practice.swd392_demo.models.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO extends DAO{

    public static AnswerDAO ins = new AnswerDAO();

    private AnswerDAO() {
        super.setIns(ins);
        if (this.ins == null) {
            try {
                super.setCon(new DBContext().getConnection());
            } catch (Exception e) {
                super.setStatus("Error at Connection" + e.getMessage());
            }
        }
    }

    public Answer getByID(int id) {
        String sql = "select * from Answer where id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                return new Answer(rs);
            }
        } catch (SQLException e) {
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }
    public Answer getByQuestionId(int id) {
        String sql = "select * from Answer where question_id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                return new Answer(rs);
            }
        } catch (SQLException e) {
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }

    public List<Answer> getList() {
        List<Answer> list = new ArrayList<>();

        String sql = "select * from Answer";
        try {
            ResultSet rs = getDataByParameter(sql);
            while (rs.next()) {
                list.add(new Answer(rs));
            }
        } catch (SQLException e) {
            super.setStatus("Error at getList:" + e.getMessage());
        }
        return list;
    }

    public void saveAnswer(Answer c){
        String sql = "insert into Answer(title, content, answer_date, answerer_id, question_id)\n" +
                "values (?,?,?,?,?)";
        try {
            getDataByParameter(sql, c.getTitle(),c.getContent(), c.getAnswerDate(), c.getAnsweredId(), c.getQuestionId());
        } catch (Exception e) {
            super.setStatus("Error at saveAnswer: " + e.getMessage());
        }
    }
    public void updateAnswer(Answer q){
        String sql = "update Answer\n" +
                "set title = ?, content =?, answer_date=?,answerer_id=?,question_id=?\n" +
                "where id = ?";
        try {
            getDataByParameter(sql, q.getTitle(), q.getContent(), q.getAnswerDate(),q.getAnsweredId(), q.getQuestionId());
        } catch (Exception e) {
            super.setStatus("Error at updateCV: " + e.getMessage());
        }
    }
    public void deleteAnswer(int userAccountID) {
        String sql = "DELETE FROM Answer WHERE id=?";
        getDataByParameter(sql, userAccountID);
    }
    
}
