package com.practice.swd392_demo.DAL;

import com.practice.swd392_demo.models.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends DAO {

    public static QuestionDAO ins = new QuestionDAO();

    private QuestionDAO() {
        super.setIns(ins);
        if (this.ins == null) {
            try {
                super.setCon(new DBContext().getConnection());
            } catch (Exception e) {
                super.setStatus("Error at Connection" + e.getMessage());
            }
        }
    }

    public Question getByID(int id) {
        String sql = "select * from Question where id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                Question q = new Question(rs);
                q.setDepartment(DepartmentDAO.ins.getByID(q.getDepartmentId()));
                q.setSender(UserDAO.ins.getByID(q.getSenderId()));
                return q;
            }
        } catch (SQLException e) {
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }

    public List<Question> getList() {
        List<Question> list = new ArrayList<>();

        String sql = "select * from Question";
        try {
            ResultSet rs = getDataByParameter(sql);
            while (rs.next()) {
                Question q = new Question(rs);
                q.setDepartment(DepartmentDAO.ins.getByID(q.getDepartmentId()));
                q.setSender(UserDAO.ins.getByID(q.getSenderId()));
                list.add(q);
            }
        } catch (SQLException e) {
            super.setStatus("Error at getList:" + e.getMessage());
        }
        return list;
    }

    public void saveQuestion(Question c) {
        String sql = "insert into Question (title, content, ask_date, sender_id,  department_id)\n" +
                "values (?,?,?,?,?)";
        try {
            getDataByParameter(sql, c.getTitle(), c.getContent(), c.getAskDate(), c.getSenderId(), c.getDepartmentId());
        } catch (Exception e) {
            System.out.println("Error at saveQuestion: " + e.getMessage());
        }
    }

    public void updateQuestion(Question q) {
        String sql = "update Question\n" +
                "set title = ?, content =?,ask_date=?,sender_id=?,department_id=?\n" +
                "where id = ?";
        try {
            getDataByParameter(sql, q.getTitle(), q.getContent(), q.getAskDate(), q.getSenderId(), q.getDepartmentId(), q.getId());
        } catch (Exception e) {
            super.setStatus("Error at updateCV: " + e.getMessage());
        }
    }

    public void deleteQuestion(int userAccountID) {
        String sql = "DELETE FROM Question WHERE id=?";
        getDataByParameter(sql, userAccountID);
    }

}
