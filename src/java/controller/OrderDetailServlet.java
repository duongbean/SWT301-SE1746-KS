/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;

/**
 *
 * @author huytu
 */
@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/orderdetail"})
public class OrderDetailServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            String service = request.getParameter("service");
            DAOOrders daoor = new DAOOrders();
            DAOOrderDetails daood = new DAOOrderDetails();
            DAOProducts daopro = new DAOProducts();
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("listAll")) {
                String id = request.getParameter("id");
                ResultSet rs = daood.getOrderDetailByID(Integer.parseInt(id));
                request.setAttribute("rsod", rs);
                request.getRequestDispatcher("showorderdetail.jsp").forward(request, response);
            }
            if (service.equals("listNew")) {
                String id = request.getParameter("id");
                ResultSet rs = daood.getOrderDetailByID(Integer.parseInt(id));
                request.setAttribute("rsod", rs);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            }
            if (service.equals("changestatus")) {
                int oid = Integer.parseInt(request.getParameter("oid"));
                String newStatus = request.getParameter("newstatus");
                daoor.updatestatus(oid, newStatus);
                response.sendRedirect("order");
            }
            if (service.equals("insert")) {
                try {
                    int oid = Integer.parseInt(request.getParameter("id")); //id cua order
                    java.util.Enumeration em = session.getAttributeNames();

                    while (em.hasMoreElements()) {
                        String raw_id = em.nextElement().toString();
                        if (!raw_id.equals("account")) {
                            try {
                                int id = Integer.parseInt(raw_id);
                                Products p = daopro.searchbyid(id);
                                Orders or = daoor.searchbyid(oid);
                                int quantity = (Integer) session.getAttribute(raw_id);
                                OrderDetails od = new OrderDetails(or, p, quantity);
                                daood.insert(od);
                                session.removeAttribute(raw_id);
                            } catch (NumberFormatException e) {

                            }
                        }
                    }
                    response.sendRedirect("orderdetail?service=listNew&id=" + oid);
                } catch (NumberFormatException e) {

                }
            }
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
