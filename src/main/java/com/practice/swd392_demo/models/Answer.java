package com.practice.swd392_demo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String title;
    private String content;
    private Date answerDate;
    private int answeredId;
    private int questionId;

    // external
    private User user;
    private Question question;

    public Answer(ResultSet rs) throws SQLException {
        this.id= rs.getInt("id");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.answerDate= rs.getDate("answer_date");
        this.answeredId = rs.getInt("answerer_id");
        this.questionId = rs.getInt("question_id");
    }
}
