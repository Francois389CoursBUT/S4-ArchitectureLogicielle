
package org.fsp.tp8boutique.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccueilServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Cas accès Admin : afficher la page connexion_admin.jsp
        if(request.getParameter("btnConnexionAdmin")!=null) {
            getServletContext().getRequestDispatcher("/connexion_admin.jsp").forward(request, response);            
        }
        // Cas accès Client : Si un client est déjà connecté (session non close) : réafficher la page
        // achats.jsp. Sinon afficher la page connexion_client.jsp.
        else if(request.getParameter("btnConnexionClient")!=null) {
            if(request.getSession().getAttribute("client") != null)
                getServletContext().getRequestDispatcher("/achats.jsp").forward(request, response);
            else 
                getServletContext().getRequestDispatcher("/connexion_client.jsp").forward(request, response);    
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
