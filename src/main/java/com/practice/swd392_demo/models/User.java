package com.practice.swd392_demo.models;

import com.practice.swd392_demo.enums.AccountRole;
import lombok.*;

import javax.management.relation.Role;

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
    private AccountRole role;
}
