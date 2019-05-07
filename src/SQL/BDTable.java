package SQL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;
import Controleurs.ChangeListener;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;
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
            conn.setAutoCommit(false); 
            conn.commit();

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
            if ((s.startsWith("INSERT")) || (s.startsWith("SELECT"))){
                conn.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            } else {
                conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            }            
            PreparedStatement stmt = conn.prepareStatement(s);
            // Affichage du résultat
            int len = s.split(",").length;
            String result = "";
            if (!(s.startsWith("SELECT"))){
                conn.commit();
                System.out.println("commit simple");
            }            
            ResultSet rset = stmt.executeQuery();
            if (s.startsWith("SELECT")){
                while (rset.next()) {
                    /**
                     * NOTE : POSE PROBLEME SI UNE SEULE ENTRÉE
                     */
                        for (int i=1; i<=len; i++){
                            result += rset.getObject(i).toString() + " ";
                        }
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
   
    public static String requeteDouble(String s) {
        // Creation de la requete
        // TODO !!!!! Faires les mêmes modifs que sur la 1ère fonction
        try {
            conn.setAutoCommit(false);  
            if ((s.startsWith("INSERT")) || (s.startsWith("SELECT"))){
                conn.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            } else {
                conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            }                  PreparedStatement stmt = conn.prepareStatement(s);

            // Affichage du résultat
            // Le problème est là  
            int len = s.split(",").length;
            String result = "";
            
            if (!(s.startsWith("SELECT"))){
                conn.commit();
                System.out.println("commit simple");
            }    
            
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
  

   
 }

