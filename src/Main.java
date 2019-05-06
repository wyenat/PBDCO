
import Capteurs.Horodateur;
import Controleurs.*;
import InterfaceGraphique.AppClient;
import InterfaceGraphique.ApplicationCapteurs;
import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;
import InterfaceGraphique.CreateurRuche;
import Ruche.Couleur;
import SQL.Affichage;
import SQL.BDTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

/**
 * Ouvre la base de données et les interfaces graphiques
 * Ferme la base de données quand les interfaces sont fermées.
 */
public class Main {
    public static void main(String args[]){
        
        //Connection à la base de données
        BDTable.connection();
        BDSurveille espion = new BDSurveille();
        try {
            // Chargement du driver Oracle
            System.out.print("Loading Oracle driver... "); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

            // Ajout du listener
            espion.addListener();

            
        }
            catch (SQLException e) {
              e.printStackTrace();
        }
        
        //Démarrage des intefaces graphiques
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppClient client = new AppClient();
                client.setVisible(true);                
                
                 client.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                 //Fermeture de la base de données
                    BDTable.fermer();
                 try {
                     espion.removeListener();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
                   }
                      });
                
                
            }
        });
    }
}
