package com.practice.swd392_demo.DAL;

import com.practice.swd392_demo.models.Department;
import com.practice.swd392_demo.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO{
    public static UserDAO ins = new UserDAO();

    private UserDAO() {
        super.setIns(ins);
        if (this.ins == null) {
            try {
                super.setCon(new DBContext().getConnection());
            } catch (Exception e) {
                super.setStatus("Error at Connection" + e.getMessage());
            }
        }
    }

    public User getByID(int id) {
        String sql="select * from [User] where id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                User u =  new User(rs);
                u.setDepartment(DepartmentDAO.ins.getByID(u.getDepartmentId()));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("Error at getByID:" + e.getMessage());
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }

}
