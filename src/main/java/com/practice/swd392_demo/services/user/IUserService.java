package com.practice.swd392_demo.services.user;

import com.practice.swd392_demo.exceptions.IdCardNoDuplicationException;
import com.practice.swd392_demo.models.User;

import java.sql.Date;

public interface IUserService {
    User addUser(String firstName, String middleName, String lastName, Date dob,
                 String idCardNo, String homeTown, int tribe, int gender,
                 Integer department) throws IdCardNoDuplicationException;
    boolean removeUser(User user);
}
