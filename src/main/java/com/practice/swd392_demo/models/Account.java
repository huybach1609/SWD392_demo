package com.practice.swd392_demo.models;


import com.practice.swd392_demo.enums.AccountRole;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;


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

    public Account(ResultSet rs) throws SQLException {
        this.id= rs.getInt("id");
        this.email= rs.getString("email");
        this.password= rs.getString("password");
        this.activationKey= rs.getString("activation_key");
        this.userId= rs.getInt("user_id");
        this.role = AccountRole.values()[rs.getInt("role_id") - 1];
    }
}
