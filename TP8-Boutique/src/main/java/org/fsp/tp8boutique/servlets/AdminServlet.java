
package org.fsp.tp8boutique.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fsp.tp8boutique.donnees.IdentifiantsException;
import org.fsp.tp8boutique.donnees.InfosProduitException;
import org.fsp.tp8boutique.javabeans.Boutique;

public class AdminServlet extends HttpServlet {

    private Boutique boutique;
    PrintWriter out;
    
    @Override
    public void init() {
        boutique = (Boutique)this.getServletContext().getAttribute("boutique");
    }
    
    private String boutonClique(HttpServletRequest request) {
        // Boutons de la page connexion_admin.jsp
        if(request.getParameter("btnSeConnecter")!=null) return "btnSeConnecter";
        else if(request.getParameter("btnSeDeconnecter")!=null) return "btnSeDeconnecter";
        // Bouton de la page admin.jsp       
        else if(request.getParameter("btnSupprimerProduit")!=null) return "btnSupprimerProduit";
        else if(request.getParameter("btnModifierProduit")!=null) return "btnModifierProduit";
        else if(request.getParameter("btnNouveauProduit")!=null) return "btnNouveauProduit";
        // Bouton de la page admin_modifier.jsp
        else if(request.getParameter("btnValiderModifs")!=null) return "btnValiderModifs";
        // Bouton de la page admin_ajouter.jsp 
        else if(request.getParameter("btnValiderAjout")!=null) return "btnValiderAjout";
        // Bouton commun aux pages admin_modifier.jsp et admin_ajouter.jsp
        else if(request.getParameter("btnAnnuler")!=null) return "btnAnnuler";
        // Bouton commun aux pages admin.jsp, admin_modifier.jsp et admin_ajouter.jsp
        else return null;       
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        
        switch(boutonClique(request)) {
            
            // Bouton de la page connexion_admin.jsp
            
            case "btnSeConnecter" :
                try {
                    boutique.verifierIdentifiantsAdmin(request.getParameter("identifiant"), 
                                            request.getParameter("motDePasse"));
                    getServletContext().getRequestDispatcher("/admin.jsp")
                        .forward(request, response);
                }
                catch(IdentifiantsException e) {
                    getServletContext().getRequestDispatcher("/connexion_admin.jsp")
                        .include(request, response);
                    out.println("<span class=\"erreur\">" +e.getMessage()+"</span>");
                }
                break;
            case "btnSeDeconnecter" :
                getServletContext().getRequestDispatcher("/accueil.html")
                        .forward(request, response);
                break;
                
            // Bouton de la page admin.jsp        
                
            case "btnModifierProduit" :
                request.setAttribute("reference", request.getParameter("produits"));
                getServletContext().getRequestDispatcher("/admin_modifier.jsp").forward(request, response);
                break;
            case "btnNouveauProduit" :
                getServletContext().getRequestDispatcher("/admin_ajouter.jsp").forward(request, response);
                break;
            
            // Bouton de la page admin_modifier.jsp                 
            case "btnValiderModifs" :
                modifierProduit(request, response);
                break;
            
            // Bouton de la page admin_ajouter.jsp 
            case "btnValiderAjout" :
                ajouterProduit(request, response);
                break;

            // Bouton commun aux page admin_modifier.jsp et admin_ajouter.jsp
            case "btnAnnuler" :
                getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
                break;
        }
    }

    public void modifierProduit(HttpServletRequest req, HttpServletResponse rep) 
                throws ServletException, IOException {
            String refProduit = req.getParameter("reference");
            String libelle = req.getParameter("libelle");
        try {
            float prix = Float.parseFloat(req.getParameter("prix"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));
            boutique.modifierProduit(refProduit, libelle, prix, quantite);
            getServletContext().getRequestDispatcher("/admin.jsp").forward(req, rep);
        }
        catch(NumberFormatException e) {
            // Quantité ou prix mal saisi(s) : réafficher la page ahats.jsp avec message d'erreur.
            getServletContext().getRequestDispatcher("/admin_modifier.jsp")
                .include(req, rep);
            out.println("<span class=\"erreur\">Quantité ou prix mal saisi(s) !</span>");
        }
        catch(InfosProduitException e) {
            // Quantité ou prix <= 0 : réafficher la page ahats.jsp avec message d'erreur.
            getServletContext().getRequestDispatcher("/admin_modifier.jsp")
                .include(req, rep);
            out.println("<span class=\"erreur\">"+e.getMessage()+"</span>");           
        }
    }
    
    public void ajouterProduit(HttpServletRequest req, HttpServletResponse rep) 
                throws ServletException, IOException {
            String refProduit = req.getParameter("reference");
            String libelle = req.getParameter("libelle");
        try {
            float prix = Float.parseFloat(req.getParameter("prix"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));
            boutique.ajouterProduit(refProduit, libelle, prix, quantite);
            getServletContext().getRequestDispatcher("/admin.jsp").forward(req, rep);
        }
        catch(NumberFormatException e) {
            // Quantité ou prix mal saisi(s) : réafficher la page ahats.jsp avec message d'erreur.
            getServletContext().getRequestDispatcher("/admin_modifier.jsp")
                .include(req, rep);
            out.println("<span class=\"erreur\">Quantité ou prix mal saisi(s) !</span>");
        }
        catch(InfosProduitException e) {
            // Quantité ou prix <= 0 : réafficher la page ahats.jsp avec message d'erreur.
            getServletContext().getRequestDispatcher("/admin_modifier.jsp")
                .include(req, rep);
            out.println("<span class=\"erreur\">"+e.getMessage()+"</span>");           
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
