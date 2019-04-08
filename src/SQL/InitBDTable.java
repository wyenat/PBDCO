package SQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;

public class InitBDTable {
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
   * @param s
   */
  public static void statement(String s) {
       // Creation de la requete
      try {
      PreparedStatement stmt = conn.prepareStatement(s);

      // Affichage du résultat
    // Le problème est là  
    //ResultSet rset = stmt.executeQuery();
      //while (rset.next ()) {
      //  System.out.println("Cinema : " + rset.getString (1)+ " - Salle : " + rset.getString (2) + "-" + rset.getString(3) + " places");
     // }
      //rset.close();
      //stmt.close();
      } catch (SQLException e) {
      System.err.println("failed");
      e.printStackTrace(System.err);
    }
 
  }
   
  /**
   * Ferme la base de donner
   */
  public static void fermer(){
            try {
                conn.close();
                System.out.println("database closed");
            } catch (SQLException ex) {
                Logger.getLogger(InitBDTable.class.getName()).log(Level.SEVERE, null, ex);
            }
  }
 }

