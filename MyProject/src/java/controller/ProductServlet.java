/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DAOCategories;
import model.DAOProducts;

/**
 *
 * @author huytu
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOProducts daopro = new DAOProducts();
        DAOCategories daocat = new DAOCategories();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("insert")) {
            String submitinsert = request.getParameter("submitinsert");
            if (submitinsert == null) {
                List<Categories> list = daocat.getAll();
                for (Categories i: list)
                request.setAttribute("listcat", list);
                request.getRequestDispatcher("insertproduct.jsp").forward(request, response);
            } else {
                int id = daopro.lastproductid()+1;
                String name = request.getParameter("name");
                Double price=0.0;
                int quantity=0;
                try{
                    price = Double.parseDouble(request.getParameter("price"));
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException e) {}
                String description = request.getParameter("description");
                String imageurl = request.getParameter("imageurl");
                Categories c = daocat.getCategorybyCategoryID(Integer.parseInt(request.getParameter("cateid")));
                Products p = new Products(id, name, price, quantity, description, imageurl, c);
                daopro.insert(p);
                response.sendRedirect("main");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
