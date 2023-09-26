/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOUsers;

/**
 *
 * @author huytu
 *///

//
@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
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
        DAOUsers dao = new DAOUsers();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String service = request.getParameter("service");
        if (service == null) {
            service = "login";
        }
        if (service.equals("login")) {
            String submit = request.getParameter("submitlogin");
            if (submit == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                String u = request.getParameter("username");
                String p = request.getParameter("password");
                Users a = dao.login(u, p);
                if (a == null) {
                    request.setAttribute("error", "Invalid username or password!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    session.setAttribute("account", a);
                    response.sendRedirect("main");
                }
            }
        }
        if (service.equals("logout")) {
            session.removeAttribute("account");
            response.sendRedirect("main");
            return;
        }
        if (service.equals("register")) {
            String submit = request.getParameter("submitregister");
            if (submit == null) {
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                int id;
                String u = request.getParameter("username");
                if (dao.checkbyusername(u)) {
                    request.setAttribute("error", "Username already exists");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    id = dao.lastuserid() + 1;
                    String p = request.getParameter("password");
                    String c = request.getParameter("contactname");
                    String phone = request.getParameter("phone");
                    String a = request.getParameter("address");
                    String b_raw = request.getParameter("birthdate");
                    java.util.Date utilDate = null;
                    try {
                        utilDate = dateFormat.parse(b_raw);
                    } catch (ParseException ex) {
                        Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.sql.Date b = new java.sql.Date(utilDate.getTime());
                    int r = 1;
                    Users user = new Users(id, c, u, p, phone, a, b, r);
                    dao.insert(user);
                    request.setAttribute("msg", "You have successfully registered. Please log in again.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
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
