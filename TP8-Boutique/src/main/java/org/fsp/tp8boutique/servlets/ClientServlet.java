package org.fsp.tp8boutique.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fsp.tp8boutique.donnees.*;
import org.fsp.tp8boutique.javabeans.Boutique;

public class ClientServlet extends HttpServlet {

    private Boutique boutique;
    PrintWriter out;
    
    @Override
    public void init() {
        boutique = (Boutique)this.getServletContext().getAttribute("boutique");
    }
    
    private String boutonClique(HttpServletRequest request) {
        // Boutons de la page connxion_client.jsp
        if(request.getParameter("btnSeConnecter")!=null) return "btnSeConnecter";
        else if(request.getParameter("btnSenregistrer")!=null) return "btnSenregistrer";
        // Boutons de la page achats.jsp
        if(request.getParameter("btnAjouterAuPanier")!=null) return "btnAjouterAuPanier";
        else if(request.getParameter("btnSupprimerDuPanier")!=null) return "btnSupprimerDuPanier";
        else if(request.getParameter("btnVoirPanier")!=null) return "btnVoirPanier";
        else if(request.getParameter("btnAfficherCommande")!=null) return "btnAfficherCommande";
        else if(request.getParameter("btnSeDeconnecter")!=null) return "btnSeDeconnecter";
        // Bouton de la page panier.jsp        
        else if(request.getParameter("btnCommander")!=null) return "btnCommander";
        // Bouton commun aux pages panier.jsp et commande.jsp
        else if(request.getParameter("btnContinuerAchats")!=null) return "btnContinuerAchats";
        else return null;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        Client client;
        Commande commande;
        switch(boutonClique(request)) {     
            
            // Boutons de la page connexion_client.jsp
            
            case "btnSeConnecter" : 
                try {
                    client = boutique.getClient(request.getParameter("identifiant"), 
                                                request.getParameter("motDePasse"));
                    request.getSession().setAttribute("client", client);
                    getServletContext().getRequestDispatcher("/achats.jsp").forward(request, response);            
                } 
                catch(IdentifiantsException e) {
                    getServletContext().getRequestDispatcher("/connexion_client.jsp")
                            .include(request, response);
                    out.println("<span class=\"erreur\">" +e.getMessage()+"</span>");
                }
                break;
            case "btnSenregistrer" : 
                try {
                    client = boutique.enregistrerClient(request.getParameter("identifiant"), 
                                                        request.getParameter("motDePasse"));
                    request.getSession().setAttribute("client", client);
                    getServletContext().getRequestDispatcher("/achats.jsp").forward(request, response);
                }
                catch(IdentifiantsException  e) {
                    getServletContext().getRequestDispatcher("/connexion_client.jsp")
                            .include(request, response);
                    out.println("<span class=\"erreur\">" +e.getMessage()+"</span>");
                }
                break;
                
            // Boutons de la page achats.jsp

            case "btnAjouterAuPanier" : 
                ajouterAuPanier(request, response);
                break;
            case "btnSupprimerDuPanier" : 
                supprimerDuPanier(request, response);
                break;
            case "btnVoirPanier" : 
                getServletContext().getRequestDispatcher("/panier.jsp")
                        .forward(request, response);
                break;
            case "btnAfficherCommande" : 
                client = (Client)request.getSession().getAttribute("client");
                int numeroCommande = Integer.parseInt(request.getParameter("commandes"));
                commande = client.getCommande(numeroCommande);
                request.getSession().setAttribute("commande", commande);
                getServletContext().getRequestDispatcher("/commande.jsp")
                          .forward(request, response);
                break;
            case "btnSeDeconnecter" :

                request.getSession().removeAttribute("client");
                getServletContext().getRequestDispatcher("/accueil.html")
                          .forward(request, response);
                break;
                        
            // Bouton de la page panier.jsp
                
            case "btnCommander" : 
                client = (Client)request.getSession().getAttribute("client");
                // Si le client n'a pas été déconnecté (après time out session), créer une commande
                // à partir du panier du client, vider le panier et afficher la page commande.jsp.
                if(client!=null) try { 
                  commande = client.getPanier().creerCommande();
                  client.getPanier().vider();                
                  request.getSession().setAttribute("commande", commande);
                  getServletContext().getRequestDispatcher("/commande.jsp")
                          .forward(request, response);
                }
                catch(QuantiteException e) {
                    getServletContext().getRequestDispatcher("/panier.jsp").include(request, response);
                    out.println("<span class=\"erreur\">" +e.getMessage()+"</span>");
                }
                break;
            
            // Bouton commun aux pages panier.jsp et commande.jsp
                
            case "btnContinuerAchats" : 
                getServletContext().getRequestDispatcher("/achats.jsp")
                        .forward(request, response);
                break;
        }    
    }

    /**
     * Ajoute au panier le produit sélectionné dans la liste déroulante des produits avec la
     * quantité saisie.
     */
    private void ajouterAuPanier(HttpServletRequest req, HttpServletResponse rep) 
                throws ServletException, IOException {
        try { 
            Client client = (Client)req.getSession().getAttribute("client");
            // Si le client n'a pas été déconnecté (après time out session), ajouter le produit choisi
            // dans le panier puis réafficher la page ahats.jsp.
            if(client!=null) { 
              Panier panier = client.getPanier();
              String refProduit = req.getParameter("produits");
              int quantite = Integer.parseInt(req.getParameter("quantite"));
              if(quantite<=0) throw new QuantiteException("Quantité négative ou nulle !");
              panier.ajouterProduit(boutique.getProduit(refProduit), quantite);
              getServletContext().getRequestDispatcher("/achats.jsp").forward(req, rep);
            }
            // Sinon, afficher la page d'accueil
            else getServletContext().getRequestDispatcher("/achats.html").forward(req, rep);
        }
        catch(NumberFormatException e) { 
            // Quantité mal saisie : réafficher la page ahats.jsp avec message d'erreur.
            getServletContext().getRequestDispatcher("/achats.jsp")
                .include(req, rep);
            out.println("<span class=\"erreur\">Quantité mal saisie !</span>");
        }
        catch(QuantiteException e) { 
            // Quantité saisie non disponible en stock : réafficher la page ahats.jsp 
            // avec message d'erreur.
            getServletContext().getRequestDispatcher("/achats.jsp")
                .include(req, rep);
            out.println("<span class=\"erreur\">"+e.getMessage()+"</span>");
        }
    }
    
    /**
     * Supprime du panier le produit sélectionné dans la liste déroulante.
     * Ne fait rien si le produit sélectionné n'est pas dans le panier.
     */
    private void supprimerDuPanier(HttpServletRequest req, HttpServletResponse rep) 
                throws ServletException, IOException {
        Client client = (Client)req.getSession().getAttribute("client");
        // Si le client n'a pas été déconnecté (après time out session), supprimer du panier 
        // le produit choisi puis réafficher la page ahats.jsp.
        if(client!=null) { 
          Panier panier = client.getPanier();
          String refProduit = req.getParameter("produits");
          panier.supprimerProduit(refProduit);
          getServletContext().getRequestDispatcher("/achats.jsp").forward(req, rep);
        }
        // Sinon, afficher la page d'accueil
        else getServletContext().getRequestDispatcher("/accueil.html").forward(req, rep);
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
