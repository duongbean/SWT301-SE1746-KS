/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huytu
 */
public class DBconnect {

    public Connection conn = null;

    public DBconnect(String url, String userName, String password) {
        try {
            //call Driver
            //com.microsoft.sqlserver.jdbc.SQLServerDriver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection: string connection
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getData(String sqlQuery) {
        ResultSet rs = null;
        Statement statement;
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    //use to test connecttion
    public DBconnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SamsungStore", "sa", "0000");
    }

    public static void main(String[] args) {
        new DBconnect();
    }
}