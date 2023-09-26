/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huytu
 */
public class DAOOrderDetails extends DBconnect {

    public void insert(OrderDetails od) {
        String sql = "INSERT INTO [dbo].[OrderDetails]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[Quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrder().getOrderID());
            pre.setInt(2, od.getProduct().getProductID());
            pre.setInt(3, od.getQuantity());
            ResultSet rs = pre.executeQuery();

        } catch (SQLException e) {
        }
    }
    
    public ResultSet getOrderDetailByID(int id) {
        String sql = "Select * from OrderDetails where OrderID="+id;
        ResultSet rs=getData(sql);
        return rs;
    }
}
