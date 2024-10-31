package com.practice.swd392_demo.models;


import com.practice.swd392_demo.enums.AccountRole;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Account {
    private int id;
    private String email;
    private String password;
    private String activationKey;
    private int userId;
    private int roleId;

    private User user;
    private AccountRole role;
}
