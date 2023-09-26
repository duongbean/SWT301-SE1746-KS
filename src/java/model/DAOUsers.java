/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huytu
 */
public class DAOUsers extends DBconnect {

    public List<Users> getAdmin() {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Role=0";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Users a = new Users(rs.getInt("UserID"),
                        rs.getString("ContactName"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getDate("BirthDate"),
                        rs.getInt("Role"));
                list.add(a);
            }
        } catch (SQLException e) {

        }

        return list;
    }
    
    public Users searchbyid(int id) {
        String sql = "SELECT * FROM Users WHERE UserID=" + id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Users u = new Users(rs.getInt("UserID"),
                rs.getString("ContactName"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("Phone"),
                rs.getString("Address"),
                rs.getDate("BirthDate"),
                rs.getInt("Role"));
                return u;
            }

        } catch (SQLException e) {

        }
        return null;
    }
    
    public int lastuserid() {
        String sql = "SELECT TOP 1 UserID\n"
                + "FROM Users\n"
                + "ORDER BY UserID DESC";
        int n=0;
        try{
            ResultSet rs = getData(sql);
            if (rs.next())
                n=rs.getInt("UserID");
        } catch (SQLException e){
        }
        return n;
    }
    

    public Users login(String username, String password) {
        String sql = "SELECT [UserID]\n"
                + "      ,[ContactName]\n"
                + "      ,[Username]\n"
                + "      ,[Password]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[BirthDate]\n"
                + "      ,[Role]\n"
                + "  FROM [dbo].[Users]\n"
                + "  WHERE Username='" + username + "' and Password='" + password + "'";
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Users u = new Users(rs.getInt("UserID"),
                        rs.getString("ContactName"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getDate("BirthDate"),
                        rs.getInt("Role"));
                return u;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean checkbyusername(String username) {
        String sql = "SELECT [UserID]\n"
                + "      ,[ContactName]\n"
                + "      ,[Username]\n"
                + "      ,[Password]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[BirthDate]\n"
                + "      ,[Role]\n"
                + "  FROM [dbo].[Users]\n"
                + "  WHERE Username='" + username + "'";
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void insert(Users u) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([UserID]\n"
                + "           ,[ContactName]\n"
                + "           ,[Username]\n"
                + "           ,[Password]\n"
                + "           ,[Phone]\n"
                + "           ,[Address]\n"
                + "           ,[BirthDate]\n"
                + "           ,[Role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, u.getUserID());
            pre.setString(2, u.getContactName());
            pre.setString(3, u.getUsername());
            pre.setString(4, u.getPassword());
            pre.setString(5, u.getPhone());
            pre.setString(6, u.getAddress());
            pre.setDate(7, u.getBirthDate());
            pre.setInt(8, u.getRole());
            ResultSet rs = pre.executeQuery();

        } catch (SQLException e) {
        }
    }
}
