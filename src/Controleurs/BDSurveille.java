/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import java.sql.*;
import java.util.*;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;


/**
 *
 * @author enguerran
 */
public class BDSurveille {

        static final String USERNAME = "matterv";
    static final String PASSWD = "matterv";
    static final String URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
   
   // Objet à conserver pour pouvoir arrêter la surveillance (correspond au thread de surveillance)
   private DatabaseChangeRegistration dcr = null;
   
   // Méthode qui crée une connexion à la BD
   //
  private OracleConnection connect() throws SQLException   {
     System.out.print("Connecting to the database... "); 
     OracleConnection conn = (OracleConnection) DriverManager.getConnection(URL, USERNAME, PASSWD);
     System.out.println("connected");
     return conn;
   }
   
   // Méthode ajoutant le listener (dans une connexion indépendante)
   //
   public void addListener() throws SQLException {
     // Connexion à la BD
     OracleConnection conn = connect();
     
     // Préparation du thread de surveillance
     Properties prop = new Properties();
     prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
     prop.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION, "true");
     // Création du thread de surveillance
     dcr = conn.registerDatabaseChangeNotification(prop);
     try {
        // Création et enregistrement du listener
        ChangeListener listener = new ChangeListener(this);
        dcr.addListener(listener);  

        // Requête pour préciser les tables à surveiller
        Statement stmt = conn.createStatement();
        ((OracleStatement)stmt).setDatabaseChangeRegistration(dcr);
        ResultSet rs = stmt.executeQuery("select * from Mesure");
        // Le parcours du résultat est nécessaire
        while (rs.next()) {}
        // Fermeture des objets
        rs.close();
        stmt.close();
        // Affichage des tables surveillées (uniquement pour la démonstration)
        String[] tableNames = dcr.getTables();
        for (int i=0;i<tableNames.length;i++)
          System.out.println(tableNames[i]+" is part of the registration.");
     }
     catch (SQLException e) {
       // Suppression du dcr en cas d'exception (arrêt du thread de surveillance)
       if (conn != null) conn.unregisterDatabaseChangeNotification(dcr);
       throw e;
     }
     finally {
       try  {
         // Toujours se déconnecter
         conn.close();
       }
       catch (Exception e)   {
         e.printStackTrace();
       }
     }
   }  
   
    // Méthode supprimant le listener (arrêt du thread de surveillance)
    //
    public void removeListener() throws SQLException {
            //Dans une connexion indépendante
            OracleConnection conn = connect();
       try {
         // Arrêt du thread de surveillance
          conn.unregisterDatabaseChangeNotification(dcr);
       }
       catch (Exception e)   {
         e.printStackTrace();
       }
       // Déconnexion
        conn.close();
    }
          
 
   
   // Méthode affichant les notifications (appelée par le listener)
   //
   public void refresh(DatabaseChangeEvent e) {
     System.out.println("Notification de changement dans la BD :");
     // Affichage des détails de l'event
     System.out.println(e.toString());
   }
   
   // Démo : ajout de tuples à la demande dans la table Test_listener(value int)
   //
   public void run() throws SQLException {

        // Connextion à la BD
       OracleConnection conn = connect();
       // Contrôle manuel des transactions
       conn.setAutoCommit(false);
       // Boucle principale
       String reponse = "";
        int i = 1;
        Scanner scan = new Scanner(System.in);
       PreparedStatement pstmt = conn.prepareStatement("insert into Test_listener values (?)");
       
        while (!reponse.equals("n") && !reponse.equals("N")) {
         System.out.println("Ajouter un tuple à Test_listener ? (o/n)");
          reponse = scan.nextLine();
         if (reponse.equals("o") || reponse.equals("O") )   {
           // Ajout d'un tuple dans Test_listener
           pstmt.setInt(1, i);
           pstmt.executeUpdate();
            i++;
           // Validation de la transaction
           conn.commit();
          }
       }
        // Fermeture de la connexion à la BD à la sortie de boucle
       pstmt.close();
        conn.close();
        System.out.println("Terminé !");
    }
}
