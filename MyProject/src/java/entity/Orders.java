/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author huytu
 */
public class Orders {
    private int OrderID;
    private Timestamp OrderDate;
    private String Status;
    private Users Customer, Admin;
    private String ReceiverName, ReceiverPhone, ReceiverAddress;
    private double Total;

    public Orders() {
    }

    public Orders(int OrderID, Timestamp OrderDate, String Status, Users Customer, Users Admin, String ReceiverName, String ReceiverPhone, String ReceiverAddress, double Total) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.Status = Status;
        this.Customer = Customer;
        this.Admin = Admin;
        this.ReceiverName = ReceiverName;
        this.ReceiverPhone = ReceiverPhone;
        this.ReceiverAddress = ReceiverAddress;
        this.Total = Total;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Timestamp getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Timestamp OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Users getCustomer() {
        return Customer;
    }

    public void setCustomer(Users Customer) {
        this.Customer = Customer;
    }

    public Users getAdmin() {
        return Admin;
    }

    public void setAdmin(Users Admin) {
        this.Admin = Admin;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String ReceiverName) {
        this.ReceiverName = ReceiverName;
    }

    public String getReceiverPhone() {
        return ReceiverPhone;
    }

    public void setReceiverPhone(String ReceiverPhone) {
        this.ReceiverPhone = ReceiverPhone;
    }

    public String getReceiverAddress() {
        return ReceiverAddress;
    }

    public void setReceiverAddress(String ReceiverAddress) {
        this.ReceiverAddress = ReceiverAddress;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    
}
