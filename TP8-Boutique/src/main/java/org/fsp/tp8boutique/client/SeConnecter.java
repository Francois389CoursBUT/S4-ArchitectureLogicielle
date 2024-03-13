package org.fsp.tp8boutique.client;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fsp.tp8boutique.donnees.Client;
import org.fsp.tp8boutique.javabeans.Boutique;
import org.fsp.tp8boutique.util.Chatroom;

public class SeConnecter extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Boutique boutique = (Boutique) getServletContext().getAttribute("boutique");
        String login = request.getParameter("login");
        String mdp = request.getParameter("password");

        try {
            Client client = boutique.getClient(login, mdp);
            request.getSession().setAttribute("client", client);
            getServletContext().getRequestDispatcher("/client/page_achats.jsp").forward(request, response);
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/accueil.html")
                    .include(request, response);
            out.println("<span class=\"erreur\">" + e.getMessage() + "</span>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
