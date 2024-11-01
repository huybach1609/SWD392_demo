package com.practice.swd392_demo.DAL;

import com.practice.swd392_demo.models.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DAO{
    public static AccountDAO ins = new AccountDAO();

    private AccountDAO() {
        super.setIns(ins);
        if (this.ins == null) {
            try {
                super.setCon(new DBContext().getConnection());
            } catch (Exception e) {
                super.setStatus("Error at Connection" + e.getMessage());
            }
        }
    }

    public Account getByID(int id) {
        String sql = "select * from Account where id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                return new Account(rs);
            }
        } catch (SQLException e) {
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }
    public Account getByUID(int id) {
        String sql = "select * from Account where user_id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                return new Account(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error at getByID:" + e.getMessage());
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }

}
