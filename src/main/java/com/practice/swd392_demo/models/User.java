package com.practice.swd392_demo.models;

import com.practice.swd392_demo.enums.AccountRole;
import lombok.*;

import java.sql.Date;

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
    private String homeTown;
    private int tribeId;
    private int genderId;
    private Integer departmentId;

    private Tribe tribe;
    private Gender gender;
    private Department department;
}
