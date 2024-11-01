package com.practice.swd392_demo.models;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Department {
    private int id;
    private String departmentName;

    public Department(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.departmentName= rs.getString("name");

    }
}
