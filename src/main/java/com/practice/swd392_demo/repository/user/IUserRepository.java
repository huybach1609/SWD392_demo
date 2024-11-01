package com.practice.swd392_demo.repository.user;

import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.IRepository;

public interface IUserRepository extends IRepository<User, Integer> {
    User searchByIdCardNo(String idCardNo);
}
