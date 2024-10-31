package com.practice.swd392_demo.repository.user;

import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.Repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository extends Repository implements IUserRepository {
    @Override
    public User add(User object) {
        query = "INSERT INTO [User] (first_name, middle_name, last_name, dob, id_card_no, hometown, tribe_id, gender_id, department_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getFirstName());
            ps.setString(2, object.getMiddleName());
            ps.setString(3, object.getLastName());
            ps.setDate(4, object.getDob());
            ps.setString(5, object.getIdCardNo());
            ps.setString(6, object.getHomeTown());
            ps.setInt(7, object.getTribeId());
            ps.setInt(8, object.getGenderId());
            if(object.getDepartmentId() != null) {
                ps.setInt(9, object.getDepartmentId());
            }
            else{
                ps.setNull(9, Types.NULL);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);
                    object.setId(generatedId);  // Set the generated ID to the Account object
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return object;
    }

    @Override
    public boolean remove(Integer integer) {
        query="Delete from User where id=?";
        try {
            connection = getConnection();
            ps=connection.prepareStatement(query);
            ps.setInt(1, integer);
            int rows=ps.executeUpdate();
            if(rows > 0){
                return true;
            }
        }catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }

    @Override
    public User searchByIdCardNo(String idCardNo) {
        User user = null;
        query = "SELECT * FROM [User] WHERE id_card_no = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, idCardNo);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setDob(resultSet.getDate("dob"));
                user.setIdCardNo(resultSet.getString("id_card_no"));
                user.setHomeTown(resultSet.getString("hometown"));
                user.setTribeId(resultSet.getInt("tribe_id"));
                user.setGenderId(resultSet.getInt("gender_id"));
                user.setDepartmentId(resultSet.getInt("department_id"));
            }
        } catch (ClassNotFoundException | SQLException e) {
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
