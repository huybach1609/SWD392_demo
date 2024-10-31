package com.practice.swd392_demo.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
public class DAO {
    public static DAO ins = new DAO();
    private Connection con;
    private String status;
    protected DAO(){
        if (this.ins == null) {
            try{
                con = new DBContext().getConnection();
            }catch(Exception e){
               status = "Error at Connection" + e.getMessage();
            }
        }
    }

    public void setIns(DAO ins){
       this.ins = ins ;
    }
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultSet getData(String sql){
        try{
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           return rs;
        } catch (SQLException e) {
            status = "Error connection at getData: " + e.getMessage();
        }
        return null;
    }
    public ResultSet getDataByParameter(String sql, Object... params){
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            for(int i = 0; i < params.length; i++){
               ps.setObject(i+1, params[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            status = "Error connection at getData: " + e.getMessage();
            System.out.println(status);
        }
        return null;
    }

    public void noReturnByParameter(String sql, Object... params) {
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            for(int i = 0; i < params.length; i++){
                ps.setObject(i+1, params[i]);
            }
            ps.executeQuery();
            ps.close();
        }catch (Exception e){
            status = "Error connection at getData: " + e.getMessage();
        }

    }
}
