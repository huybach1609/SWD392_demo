package com.practice.swd392_demo.services.user;

import com.practice.swd392_demo.models.User;

import java.sql.Date;

public interface IUserService {
    User addUser(String firstName, String middleName, String lastName, Date dob,
                 String idCardNo, String homeTown, Tribe tribe, Gender gender,
                 Department department);
}
