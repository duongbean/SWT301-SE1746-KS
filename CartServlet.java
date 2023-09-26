/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author huytu
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
            if (service == null) {
                service = "listAll";
            }

            switch (service) {
                case "listAll":
                    handleListAll(request, response);
                    break;
                case "addCart":
                    handleAddCart(request, session, response);
                    break;
                case "update":
                    handleUpdate(request, session, response);
                    break;
                case "remove":
                    handleRemove(request, session, response);
                    break;
                default:
                    // Handle invalid service
                    break;
            }
        }
    }

    private void handleListAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("showcart.jsp").forward(request, response);
    }

    private void handleAddCart(HttpServletRequest request, HttpSession session, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (session.getAttribute(id) == null) {
            session.setAttribute(id, 1);
        } else {
            int quantity = (Integer) session.getAttribute(id);
            int count = quantity + 1;
            session.setAttribute(id, count);
        }
        request.getRequestDispatcher("showcart.jsp").forward(request, response);
    }

    private void handleUpdate(HttpServletRequest request, HttpSession session, HttpServletResponse response)
            throws ServletException, IOException {
        java.util.Enumeration em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            String raw_id = em.nextElement().toString();
            if (!raw_id.equals("account")) {
                String raw_quantity = request.getParameter(raw_id);
                int quantity = Integer.parseInt(raw_quantity);
                session.setAttribute(raw_id, quantity);
            }
        }
        response.sendRedirect("cart");
    }

    private void handleRemove(HttpServletRequest request, HttpSession session, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String quantity_raw = request.getParameter("quantity");
        java.util.Enumeration em = session.getAttributeNames();
        if (id == null) {
            while (em.hasMoreElements()) {
                String raw_id = em.nextElement().toString();
                if (!raw_id.equals("account")) {
                    session.removeAttribute(raw_id);
                }
            }
        } else {
            if (quantity_raw != null) {
                while (em.hasMoreElements()) {
                    String raw_id = em.nextElement().toString();
                    if (!raw_id.equals("account")) {
                        if (((int) session.getAttribute(raw_id)) == 1) {
                            session.removeAttribute(raw_id);
                        }
                    }
                }
            } else {
                session.removeAttribute(id);
            }
        }
        response.sendRedirect("cart");
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
