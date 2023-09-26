/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author huytu
 */
public class Categories {
    private int categoryID;
    private String categoryName, description;

    public Categories() {
    }

    public Categories(int CategoryID, String CategoryName, String Description) {
        this.categoryID = CategoryID;
        this.categoryName = CategoryName;
        this.description = Description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.categoryID = CategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.categoryName = CategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    
    
}
