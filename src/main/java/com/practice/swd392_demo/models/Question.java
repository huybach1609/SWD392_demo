package com.practice.swd392_demo.models;

import lombok.*;

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
    private int userId;
    private int departmentId;

//   external
    private List<Answer> answerList;
    private User user;
    private Department department;
}
