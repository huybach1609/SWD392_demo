package com.practice.swd392_demo.models;

import com.practice.swd392_demo.enums.Gender;
import lombok.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String idCardNo;
    private String hometown;
    private int tribeId;
    private int genderId;
    private Integer departmentId;

    private Tribe tribe;
    private Gender gender;
    private Department department;

    public User(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.firstName = rs.getString("first_name");
        this.middleName = rs.getString("middle_name");
        this.lastName = rs.getString("last_name");
        this.dob = rs.getDate("dob");
        this.hometown = rs.getString("hometown");
        this.tribeId = rs.getInt("tribe_id");
        this.departmentId = rs.getInt("department_id");

        // Assuming that gender_id corresponds to the ordinal values of Gender enum
        this.gender = Gender.values()[rs.getInt("gender_id")];

    }
}
