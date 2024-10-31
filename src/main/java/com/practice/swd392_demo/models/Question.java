package com.practice.swd392_demo.models;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private int id;
    private String title;
    private String content;
    private Date askDate;
    private int senderId;
    private int departmentId;

//   external
    private User sender;
    private Department department;

    public Question(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.askDate = rs.getDate("ask_date");
        this.senderId = rs.getInt("sender_id");
        this.departmentId = rs.getInt("department_id");
    }

}
