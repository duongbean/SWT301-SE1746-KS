/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import entity.Products;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.DAOCategories;
import model.DAOProducts;

/**
 *
 * @author huytu
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            doPost(request, response);
        }
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
        HttpSession session = request.getSession();
        DAOProducts daopro = new DAOProducts();
        DAOCategories daocat = new DAOCategories();
        //Tao service
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        //Buoc khoi dau vao web
        if (service.equals("listAll")) {
            //Lay list Products nho categoryid
            String id_raw = request.getParameter("id");
            if (id_raw == null) {
                id_raw = "0";
            }
            int id;
            List<Products> listpro;
            try {
                id = Integer.parseInt(id_raw);
                listpro = daopro.getProductsbyCategoryID(id);
                request.setAttribute("datapro", listpro);
            } catch (NumberFormatException e) {
            }
            //Lay list Categories
            List<Categories> listcat = daocat.getAll();
            request.setAttribute("datacat", listcat);
            //Kiem tra quyen han, neu la admin thi sang trang admin.jsp, con lai sang index
            Users a;
            if (session.getAttribute("account") != null) {
                a = (Users) session.getAttribute("account");
                if (a.getRole() == 0) {
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                    return;
                }
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (service.equals("search")) {
            String submit = request.getParameter("searchbutton");
            if (submit == null) {
                request.getRequestDispatcher("search.jsp").forward(request, response);
            } else {
                String s = request.getParameter("string");
                List<Products> listpro = daopro.getProductsbyString(s);
                request.setAttribute("datapro", listpro);
                //Lay list Categories
                List<Categories> listcat = daocat.getAll();
                request.setAttribute("datacat", listcat);
                //Kiem tra quyen han, neu la admin thi sang trang admin.jsp, con lai sang index
                Users a;
                if (session.getAttribute("account") != null) {
                    a = (Users) session.getAttribute("account");
                    if (a.getRole() == 0) {
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                        return;
                    }
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
