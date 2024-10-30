package com.practice.swd392_demo.services.user;

import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.user.IUserRepository;

import java.sql.Date;

public class UserService implements IUserService{
    public IUserRepository _userRepository;
    public UserService(IUserRepository userRepository){
        _userRepository = userRepository;
    }

    @Override
    public User addUser(String firstName, String middleName, String lastName, Date dob, String idCardNo, String homeTown, Tribe tribe, Gender gender, Department department) {
        return null;
    }
}
