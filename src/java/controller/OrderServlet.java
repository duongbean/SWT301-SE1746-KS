/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
import entity.Products;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.DAOOrders;
import model.DAOProducts;
import model.DAOUsers;

/**
 *
 * @author huytu
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        DAOOrders daoor = new DAOOrders();
        DAOUsers daou = new DAOUsers();
        DAOProducts daopro = new DAOProducts();
        String[] submitorder = request.getParameterValues("submitorder");
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        
        if (service.equals("listAll")) {
            Users u = (Users) session.getAttribute("account");
            ResultSet rs = null;
            //Lay list Orders nho Status
            String status = request.getParameter("status");
            if (u.getRole() == 0) {
                rs = daoor.searchbyad(u.getUserID(),status);
            } else {
                rs = daoor.searchbycus(u.getUserID(),status);
            }
            request.setAttribute("rsor", rs);
            request.getRequestDispatcher("showorder.jsp").forward(request, response);
        }
        
        
        
        if (service.equals("insert")) {
            if (submitorder == null) {
                ResultSet lista = daou.getData("Select * from Users where Role=0");
                request.setAttribute("dataa", lista);
                request.getRequestDispatcher("insertorder.jsp").forward(request, response);

            } else {
                int oid = daoor.lastorderid() + 1;
                LocalDateTime currentDateTime = LocalDateTime.now();
                Timestamp orderDate = Timestamp.valueOf(currentDateTime);
                String status = "Wait";
                Users c = (Users) session.getAttribute("account");
                Users a = daou.searchbyid(Integer.parseInt(request.getParameter("adminid")));
                String receivername = request.getParameter("receivername");
                String receiverphone = request.getParameter("receiverphone");
                String receiveraddress = request.getParameter("receiveraddress");
                java.util.Enumeration em = session.getAttributeNames();
                double grandtotal = 0;

                while (em.hasMoreElements()) {
                    String raw_id = em.nextElement().toString();
                    if (!raw_id.equals("account")) {
                        int id = Integer.parseInt(raw_id);
                        Products pro = daopro.searchbyid(id);
                        grandtotal = grandtotal + pro.getPrice() * ((int) session.getAttribute(raw_id));
                    }
                }
                Orders or = new Orders(oid, orderDate, status, c, a, receivername, receiverphone, receiveraddress, grandtotal);
                daoor.insert(or);
                response.sendRedirect("orderdetail?id=" + oid + "&service=insert");
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
