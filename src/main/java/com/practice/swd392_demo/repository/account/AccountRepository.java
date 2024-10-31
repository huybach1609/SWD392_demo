package com.practice.swd392_demo.repository.account;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.Account;
import com.practice.swd392_demo.repository.Repository;
import com.practice.swd392_demo.repository.user.UserRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountRepository extends Repository implements IAccountRepository{
    @Override
    public Account add(Account object) {
        query = "INSERT INTO [Account] (email, password, activation_key, user_id, role_id) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getEmail());
            ps.setString(2, object.getPassword());
            ps.setString(3, object.getActivationKey());
            ps.setInt(4, object.getUserId());// Assuming AccountRole is an Enum
            ps.setInt(5, object.getRoleId());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);
                    object.setId(generatedId);  // Set the generated ID to the Account object
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return object;
    }

    @Override
    public boolean remove(Integer integer) {
        query="Delete from Account where id=?";
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
    public Account searchByEmail(String email) {
        Account account = null;
        String query = "SELECT * FROM [Account] WHERE Email = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setEmail(resultSet.getString("Email"));
                account.setPassword(resultSet.getString("Password"));
                account.setActivationKey(resultSet.getString("ActivationKey"));
                account.setRole(AccountRole.valueOf(resultSet.getString("Role"))); // Assuming AccountRole is an Enum
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return account;
    }
}
