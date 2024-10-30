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
    private String Email;
    private String Password;
    private String ActivationKey;
    private AccountRole role;
}
