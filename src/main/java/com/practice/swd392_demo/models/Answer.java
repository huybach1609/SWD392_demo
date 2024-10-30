package com.practice.swd392_demo.models;

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
}
