package com.practice.swd392_demo.repository.account;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.Account;
import com.practice.swd392_demo.repository.Repository;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountRepository extends Repository implements IAccountRepository{
    @Override
    public void add(Account object) {
        query = "INSERT INTO Account (Email, Password, ActivationKey, Role) VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, object.getEmail());
            ps.setString(2, object.getPassword());
            ps.setString(3, object.getActivationKey());
            ps.setString(4, object.getRole().toString()); // Assuming AccountRole is an Enum

            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public Account searchByEmail(String email) {
        Account account = null;
        String query = "SELECT * FROM Accounts WHERE Email = ?";
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
