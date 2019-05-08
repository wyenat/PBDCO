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
     OracleConnection conn = (OracleConnection) DriverManager.getConnection(URL, USERNAME, PASSWD);
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
     
   }
   
   // Démo : ajout de tuples à la demande dans la table Test_listener(value int)
   //
   public void run() throws SQLException {

    }
}
