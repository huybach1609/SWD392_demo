package com.practice.swd392_demo.DAL;

import com.practice.swd392_demo.models.Department;
import com.practice.swd392_demo.models.Department;
import com.practice.swd392_demo.models.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends DAO{
    public static DepartmentDAO ins = new DepartmentDAO();
    private DepartmentDAO() {
        super.setIns(ins);
        if (this.ins == null) {
            try {
                super.setCon(new DBContext().getConnection());
            } catch (Exception e) {
                super.setStatus("Error at Connection" + e.getMessage());
            }
        }
    }
    public List<Department> getList() {
        List<Department> list = new ArrayList<>();

        String sql = "select * from Department";
        try {
            ResultSet rs = getDataByParameter(sql);
            while (rs.next()) {
                list.add(new Department(rs));
            }
        } catch (SQLException e) {
            super.setStatus("Error at getList:" + e.getMessage());
        }
        return list;
    }

    public Department getByID(int id) {
        String sql = "select * from Department where id = ?";
        try {
            ResultSet rs = getDataByParameter(sql, id);
            if (rs.next()) {
                return new Department(rs);
            }
        } catch (SQLException e) {
            super.setStatus("Error at getByID:" + e.getMessage());
        }
        return null;
    }


}
