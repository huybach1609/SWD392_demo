package com.practice.swd392_demo.services.user;

import com.practice.swd392_demo.exceptions.IdCardNoDuplicationException;
import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.user.IUserRepository;

import java.sql.Date;

public class UserService implements IUserService{
    public IUserRepository _userRepository;
    public UserService(IUserRepository userRepository){
        _userRepository = userRepository;
    }

    @Override
    public User addUser(String firstName, String middleName, String lastName,
                        Date dob, String idCardNo, String homeTown,
                        int tribe, int gender, Integer department)
            throws IdCardNoDuplicationException {
        if(_userRepository.searchByIdCardNo(idCardNo) != null){
            throw new IdCardNoDuplicationException();
        }
        User createdUser = new User();
        createdUser.setFirstName(firstName);
        createdUser.setMiddleName(middleName);
        createdUser.setLastName(lastName);
        createdUser.setDob(dob);
        createdUser.setIdCardNo(idCardNo);
        createdUser.setHometown(homeTown);
        createdUser.setTribeId(tribe);
        createdUser.setGenderId(gender);
        createdUser.setDepartmentId(department);
        return _userRepository.add(createdUser);
    }

    @Override
    public boolean removeUser(User user) {
        return _userRepository.remove(user.getId());
    }
}
