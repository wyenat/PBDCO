package SQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;

public class BDTable {
        private static final String URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
        private static final String USERNAME = "matterv";                            // A adapter pour votre compte Oracle
        private static final String PASSWD = "matterv";                             // A adapter pour votre compte Oracle
        private static Connection conn;
 
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
      PreparedStatement stmt = conn.prepareStatement(s);

      // Affichage du résultat
    // Le problème est là  
    String result = "";
    ResultSet rset = stmt.executeQuery();
    System.out.println(rset.toString());
     if (s.startsWith("SELECT")){
        while (rset.next ()) {
              /**
               * PAS TROP SUR À VÉRIFIER
               */

                   result += rset.getObject (1).toString() + " ";
          }
     }
      stmt.close();
      rset.close();
      return result;
      } catch (SQLException e) {
      System.err.println("failed");
      e.printStackTrace(System.err);
    }
      return null;
  }
   
  /**
   * Ferme la base de donner
   */
  public static void fermer(){
            try {
                conn.close();
                System.out.println("database closed");
            } catch (SQLException ex) {
                Logger.getLogger(BDTable.class.getName()).log(Level.SEVERE, null, ex);
            }
  }
 }

