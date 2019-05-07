package SQL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;
import Controleurs.ChangeListener;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

public class BDTable {
        private static final String URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
        private static final String USERNAME = "matterv";                            // A adapter pour votre compte Oracle
        private static final String PASSWD = "matterv";                             // A adapter pour votre compte Oracle
        public static Connection conn;
        private static OracleConnection connSurveille;
        // Objet à conserver pour pouvoir arrêter la surveillance (correspond au thread de surveillance)
        private static DatabaseChangeRegistration dcr = null;
 
    /**
     * Ouvre une connection à la base de données
     */
  public static void connection() {

        try {
            // Chargement du driver Oracle
            System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

            // Etablissement de la connection
            System.out.print("Connecting to the database... ");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
            System.out.println("connected");

        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
  }
  
  /**
   * Execute le statement SQL passé en argument
   * @param s la requete
     * @return le résultat de la requete, en tableau.
   */
  public static String requete(String s) {
        // Creation de la requete
        try {
            conn.setAutoCommit(false);  
            conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            PreparedStatement stmt = conn.prepareStatement(s);

            // Affichage du résultat
            // Le problème est là  
            int len = s.substring(0, s.indexOf("FROM")).split(",").length;
            System.out.println("len = " + len + "avec s = " + s.substring(0,s.indexOf("FROM"))) ;
            String result = "";
            ResultSet rset = stmt.executeQuery();
            if (s.startsWith("SELECT")){
                while (rset.next()) {
                        for (int i=1; i<=len; i++){
                            
                            result += rset.getObject(i).toString() + " ";
                        }
                }
            }
            stmt.close();
            rset.close();
            conn.commit();
            System.out.println("commit simple");
            return result;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
        return null;
    }
   
    public static String requeteDouble(String s) {
        // Creation de la requete
        try {
            conn.setAutoCommit(false);  
            conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            PreparedStatement stmt = conn.prepareStatement(s);

            // Affichage du résultat
            // Le problème est là  
            int len = s.substring(s.indexOf("SELECT", 2)).split(",").length;
            String result = "";
            ResultSet rset = stmt.executeQuery();

            if (s.startsWith("SELECT")){
                while (rset.next()) {
                    /**
                    * NOTE : POSE PROBLEME SI UNE SEULE ENTRÉE
                    */
                    for (int i=1; i<=len/2; i++){
                        result += rset.getObject(i).toString() + " ";
                    }
                }
            }
            stmt.close();
            rset.close();
            conn.commit();
            System.out.println("commit double");
            return result;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
        return null;
    }
  
  
  /**
   * Ferme la base de données
   */
  public static void fermer(){
        try {
            conn.close();
            // Suppression du listener
            System.out.println("database closed");
        } catch (SQLException ex) {
            Logger.getLogger(BDTable.class.getName()).log(Level.SEVERE, null, ex);
        }
  }  
  
  
  private static OracleConnection connectDur() throws SQLException   {
        OracleConnection conn = (OracleConnection) DriverManager.getConnection(URL, USERNAME, PASSWD);
        return conn;
   }
  
   // Méthode ajoutant le listener (dans une connexion indépendante)
   //
//   
//  public static void addListener() throws SQLException {
//     // Connexion à la BD
//     connSurveille = BDTable.connectDur();
//     
//     // Préparation du thread de surveillance
//     Properties prop = new Properties();
//     prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
//     prop.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION, "true");
//     // Création du thread de surveillance
//     dcr = connSurveille.registerDatabaseChangeNotification(prop);
//     try {
//       // Création et enregistrement du listener
//       ChangeListener listener = new ChangeListener(this);
//       dcr.addListener(listener);  
//       
//       // Requête pour préciser les tables à surveiller
//       Statement stmt = conn.createStatement();
//       ((OracleStatement)stmt).setDatabaseChangeRegistration(dcr);
//       ResultSet rs = stmt.executeQuery("select * from Test_listener");
//       // Le parcours du résultat est nécessaire
//       while (rs.next()) {}
//       // Fermeture des objets
//       rs.close();
//       stmt.close();
//       // Affichage des tables surveillées (uniquement pour la démonstration)
//       String[] tableNames = dcr.getTables();
//       for (int i=0;i<tableNames.length;i++)
//         System.out.println(tableNames[i]+" is part of the registration.");
//     }
//     catch (SQLException e) {
//       // Suppression du dcr en cas d'exception (arrêt du thread de surveillance)
//       if (conn != null) connSurveille.unregisterDatabaseChangeNotification(dcr);
//       throw e;
//     }
//     finally {
//       try  {
//         // Toujours se déconnecter
//         conn.close();
//       }
//       catch (Exception e)   {
//         e.printStackTrace();
//       }
//     }
//   }  
//   
//    // Méthode supprimant le listener (arrêt du thread de surveillance)
//    //
//    public void staticremoveListener() throws SQLException {
//       //Dans une connexion indépendante
//       OracleConnection conne = connectDur();
//       try {
//         // Arrêt du thread de surveillance
//         
//          conne.unregisterDatabaseChangeNotification(dcr);
//       }
//       catch (Exception e)   {
//         e.printStackTrace();
//       }
//       // Déconnexion
//        conn.close();
//     }
//
// 
//   
   // Méthode affichant les notifications (appelée par le listener)
   //
   public void refresh(DatabaseChangeEvent e) {
     System.out.println("Notification de changement dans la BD :");
     // Affichage des détails de l'event
     System.out.println(e.toString());
   }
 }

