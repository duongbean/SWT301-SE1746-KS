/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import entity.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huytu
 */
public class DAOProducts extends DBconnect {

    DAOCategories dao = new DAOCategories();

    public List<Products> getProductsbyCategoryID(int id) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[Price]\n"
                + "      ,[Quantity]\n"
                + "      ,[Description]\n"
                + "      ,[ImageURL]\n"
                + "      ,[CategoryID]\n"
                + "  FROM [dbo].[Products]\n"
                + "  Where 1=1";
        if (id != 0) {
            sql += " and CategoryID=" + id;
        }
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Categories c = dao.getCategorybyCategoryID(id);
                Products p = new Products(rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Description"),
                        rs.getString("ImageURL"),
                        c);
                list.add(p);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public int lastproductid() {
        String sql = "SELECT TOP 1 ProductID\n"
                + "FROM Products\n"
                + "ORDER BY ProductID DESC";
        int n = 0;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n = rs.getInt("ProductID");
            }
        } catch (SQLException e) {
        }
        return n;
    }

    public List<Products> getProductsbyString(String s) {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[Price]\n"
                + "      ,[Quantity]\n"
                + "      ,[Description]\n"
                + "      ,[ImageURL]\n"
                + "      ,[CategoryID]\n"
                + "  FROM [dbo].[Products]\n"
                + "  Where 1=1";
        if (!s.equals("All")) {
            sql += " and ProductName like '%" + s + "%'";
        }
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Categories c = dao.getCategorybyCategoryID(rs.getInt("CategoryID"));
                Products p = new Products(rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Description"),
                        rs.getString("ImageURL"),
                        c);
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void insert(Products p) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductID]\n"
                + "           ,[ProductName]\n"
                + "           ,[Price]\n"
                + "           ,[Quantity]\n"
                + "           ,[Description]\n"
                + "           ,[ImageURL]\n"
                + "           ,[CategoryID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, p.getProductID());
            pre.setString(2, p.getProductName());
            pre.setDouble(3, p.getPrice());
            pre.setInt(4, p.getQuantity());
            pre.setString(5, p.getDescription());
            pre.setString(6, p.getImageURL());
            pre.setInt(7, p.getCategory().getCategoryID());
            ResultSet rs = pre.executeQuery();
        } catch (SQLException e) {
        }
    }

    public Products searchbyid(int id) {
        String sql = "SELECT * FROM Products WHERE ProductID=" + id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Categories c = dao.getCategorybyCategoryID(rs.getInt("CategoryID"));
                Products p = new Products(rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Description"),
                        rs.getString("ImageURL"),
                        c);
                return p;
            }

        } catch (SQLException e) {

        }
        return null;
    }

}
