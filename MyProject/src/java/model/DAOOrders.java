/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import entity.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huytu
 */
public class DAOOrders extends DBconnect {

    DAOUsers dao = new DAOUsers();

    public void updatestatus(int or, String s) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [Status] = '"+s+"'\n"
                + " WHERE [OrderID]=" + or;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
        } catch (SQLException e) {}
    }

    public ResultSet searchbycus(int id, String s) {
        String sql = "Select * from Orders where CustomerID = '" + id + "'";
        if (s != null) {
            if (!s.equals("All")) {
                sql += " and Status like '" + s + "'";
            }
        }
        ResultSet rs = getData(sql);
        return rs;
    }

    public ResultSet searchbyad(int id, String s) {
        String sql = "Select * from Orders where AdminID = '" + id + "'";
        if (s != null) {
            if (!s.equals("All")) {
                sql += " and Status like '" + s + "'";
            }
        }
        ResultSet rs = getData(sql);
        return rs;
    }

    public Orders searchbyid(int id) {
        String sql = "SELECT * FROM Orders WHERE OrderID=" + id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                Users c = dao.searchbyid(rs.getInt("CustomerID"));
                Users a = dao.searchbyid(rs.getInt("AdminID"));
                Orders p = new Orders(rs.getInt("OrderID"),
                        rs.getTimestamp("OrderDate"),
                        rs.getString("Status"),
                        c,
                        a,
                        rs.getString("ReceiverName"),
                        rs.getString("ReceiverPhone"),
                        rs.getString("ReceiverAddress"),
                        rs.getDouble("Total"));
                return p;
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public int lastorderid() {
        String sql = "SELECT TOP 1 OrderID\n"
                + "FROM Orders\n"
                + "ORDER BY OrderID DESC";
        int n = 0;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n = rs.getInt("OrderID");
            }
        } catch (SQLException e) {
        }
        return n;
    }

    public void insert(Orders o) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([OrderID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[Status]\n"
                + "           ,[CustomerID]\n"
                + "           ,[AdminID]\n"
                + "           ,[ReceiverName]\n"
                + "           ,[ReceiverPhone]\n"
                + "           ,[ReceiverAddress]\n"
                + "           ,[Total])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, o.getOrderID());
            pre.setTimestamp(2, o.getOrderDate());
            pre.setString(3, o.getStatus());
            pre.setInt(4, o.getCustomer().getUserID());
            pre.setInt(5, o.getAdmin().getUserID());
            pre.setString(6, o.getReceiverName());
            pre.setString(7, o.getReceiverPhone());
            pre.setString(8, o.getReceiverAddress());
            pre.setDouble(9, o.getTotal());
            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
