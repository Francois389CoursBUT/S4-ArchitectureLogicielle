<%@page import="java.util.List"%>
<%@page import="org.fsp.tp8boutique.util.Chatroom"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- A décommenter pour rafraîchir automatiquement la page toutes les 10 secondes  
        <meta http-equiv="refresh" content="10" /> 
	-->
        <link type="text/css" rel="stylesheet" href="styles.css"/>
        <title>Chatroom</title>
    </head>
    <%  String pseudo = (String)session.getAttribute("pseudo"); %>
    <body>
      <h2 align="center">Chatroom </h2>
      Pseudo : <%= pseudo %><br><br>
      Messages déposés
      <textarea cols="30" rows="5" readonly="readonly">
<% List<String> messages = Chatroom.getInstance().getMessages();
           for (String m: messages) out.println(m);
        %>
      </textarea><br><br>
      <form action="ServletEnvoyer">
        Message à envoyer<br>
        <input  type="text" name="messageAenvoyer" size="25" value="" />
        <input type="submit" name ="btnEnvoyer" value="Envoyer"/>
      </form>
      <table>
          <tbody> <tr>
            <td>
                <form action="ServletRafraichir"> 
                    <input type="submit" name="btnRafraichir" value="Rafraîchir" size="10"/>
                </form></td>
            <td>
                <form action="ServletSeDeconnecter">
                    <input type="submit" name="btnDeconnecter" value="Se déconnecter" size="10" />
                </form></td>
            </tr>
          </tbody>
      </table>
</html>
