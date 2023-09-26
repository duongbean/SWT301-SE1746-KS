/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author huytu
 */
public class OrderDetails {
    private Orders Order;
    private Products Product;
    private int Quantity;

    public OrderDetails() {
    }

    public OrderDetails(Orders Order, Products Product, int Quantity) {
        this.Order = Order;
        this.Product = Product;
        this.Quantity = Quantity;
    }

    public Orders getOrder() {
        return Order;
    }

    public void setOrder(Orders Order) {
        this.Order = Order;
    }

    public Products getProduct() {
        return Product;
    }

    public void setProduct(Products Product) {
        this.Product = Product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
}
