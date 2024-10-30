package com.practice.swd392_demo.repository.user;

import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.Repository;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository extends Repository implements IUserRepository {
    @Override
    public void add(User object) {
        query = "INSERT INTO User (firstName, middleName, lastName, dob, idCardNo, homeTown, tribeId, genderId, departmentId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, object.getFirstName());
            ps.setString(2, object.getMiddleName());
            ps.setString(3, object.getLastName());
            ps.setDate(4, object.getDob());
            ps.setString(5, object.getIdCardNo());
            ps.setString(6, object.getHomeTown());
            ps.setInt(7, object.getTribeId());
            ps.setInt(8, object.getGenderId());
            ps.setInt(9, object.getDepartmentId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
    }
    @Override
    public User searchByIdCardNo(String idCardNo) {
        User user = null;
        query = "SELECT * FROM User WHERE idCardNo = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idCardNo);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setMiddleName(resultSet.getString("middleName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setDob(resultSet.getDate("dob"));
                user.setIdCardNo(resultSet.getString("idCardNo"));
                user.setHomeTown(resultSet.getString("homeTown"));
                user.setTribeId(resultSet.getInt("tribeId"));
                user.setGenderId(resultSet.getInt("genderId"));
                user.setDepartmentId(resultSet.getInt("departmentId"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return user;
    }
}
