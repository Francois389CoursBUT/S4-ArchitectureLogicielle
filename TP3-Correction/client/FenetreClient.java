package client;

import interface_serveur.Serveur;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JOptionPane;


public class FenetreClient extends javax.swing.JFrame {
    
    private Serveur serveur;
    
    /**
     * Afficher un message reçu.
     */
    public void afficherMessage(String message) {
        txtMessagesDeposes.append(message+"\n");
    }
    
    /** Creates new form FenetreClient. */
    public FenetreClient() {
        initComponents();
        txtMessageAEnvoyer.setEnabled(false);
        btnEnvoyer.setEnabled(false);
        txtMessagesDeposes.setEditable(false);        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUrlServeur = new javax.swing.JTextField();
        btnConnecter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnEnvoyer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessagesDeposes = new javax.swing.JTextArea();
        btnRafraichir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPseudo = new javax.swing.JTextField();
        txtMessageAEnvoyer = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Url Serveur");

        btnConnecter.setText("Connecter");
        btnConnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnecterAction(evt);
            }
        });

        jLabel2.setText("Message à envoyer");

        btnEnvoyer.setText("Envoyer");
        btnEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvoyerAction(evt);
            }
        });

        jLabel3.setText("Messages déposés");

        txtMessagesDeposes.setEditable(false);
        txtMessagesDeposes.setColumns(20);
        txtMessagesDeposes.setRows(5);
        jScrollPane2.setViewportView(txtMessagesDeposes);

        btnRafraichir.setText("Rafraichir");
        btnRafraichir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRafraichirAction(evt);
            }
        });

        jLabel4.setText("Pseudo");

        txtMessageAEnvoyer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMessageAEnvoyerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 303, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRafraichir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMessageAEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEnvoyer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(47, 47, 47)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPseudo)
                            .addComponent(txtUrlServeur, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConnecter)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUrlServeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnecter))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRafraichir)
                        .addGap(68, 68, 68)))
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessageAEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnvoyer))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** 
     * Envoyer à l'objet serveur le message saisi dans txtMessageAenvoyer avec le pseudo saisi
     * dans txtPseudo.
    */
    private void btnEnvoyerAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvoyerAction
      if (!txtMessageAEnvoyer.getText().equals("")) 
        try {
            String message = txtMessageAEnvoyer.getText();
            // Envoi message au serveur
            serveur.postMessage(message, txtPseudo.getText());
       } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Connexion serveur imposible.\n"+e);
       }
}//GEN-LAST:event_btnEnvoyerAction

/** Recherche dans le serveur d'objets RMI (identifié par l'url saisie dans txtUrlServeur) 
 *  l'objet "serveur" et le mémorise dans serveur, puis affiche dans txtMessagesDeposes les
 *  messages renvoyés par serveur.getMessages().
 * Affiche un message d'erreur si url non valide ou pseudo vide.
 */
private void btnConnecterAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnecterAction
    if (txtUrlServeur.getText().isEmpty())
            JOptionPane.showMessageDialog(this,"Url serveur non saisie!");
    else if (txtPseudo.getText().isEmpty())
            JOptionPane.showMessageDialog(this,"Pseudo non saisi!");
    else try {
        serveur = (Serveur) Naming.lookup(txtUrlServeur.getText());   
        List<String> messages = serveur.getMessages();
        for(String message: messages) 
          txtMessagesDeposes.append(message+"\n");
        txtUrlServeur.setEnabled(false);
        txtPseudo.setEnabled(false);
        btnConnecter.setEnabled(false);
        txtMessageAEnvoyer.setEnabled(true);
        btnEnvoyer.setEnabled(true);
        txtMessageAEnvoyer.setText("Saisir votre message"); // Remettre à vide si reconnexion           
    }catch (Exception e) {
        JOptionPane.showMessageDialog(this,"Connexion serveur imposible.\n"+e);
    }
}//GEN-LAST:event_btnConnecterAction

  /**
   * Réafficher dans txtMessagesDeposes les messages renvoués par erveur.getMessages().
   */
  private void btnRafraichirAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRafraichirAction
      try {
        List<String> messages = serveur.getMessages();
        txtMessagesDeposes.setText("");
        for(String message : messages)
          txtMessagesDeposes.append(message+"\n");
      } catch (RemoteException e) {
        JOptionPane.showMessageDialog(this,"Erreur d'accès au serveur.\n"+e);
      }
  }//GEN-LAST:event_btnRafraichirAction

  private void txtMessageAEnvoyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMessageAEnvoyerMouseClicked
    txtMessageAEnvoyer.setText("");
  }//GEN-LAST:event_txtMessageAEnvoyerMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnecter;
    private javax.swing.JButton btnEnvoyer;
    private javax.swing.JButton btnRafraichir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtMessageAEnvoyer;
    private javax.swing.JTextArea txtMessagesDeposes;
    private javax.swing.JTextField txtPseudo;
    private javax.swing.JTextField txtUrlServeur;
    // End of variables declaration//GEN-END:variables
    
}
