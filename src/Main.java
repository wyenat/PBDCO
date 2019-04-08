
import InterfaceGraphique.AppClient;
import InterfaceGraphique.ApplicationCapteurs;
import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;
import InterfaceGraphique.ApplicationClient;
import Ruche.Fabrique;
import SQL.InitBDTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ouvre la base de données et les interfaces graphiques
 * Ferme la base de données quand les interfaces sont fermées.
 */
public class Main {
    public static void main(String args[]){
        
        //Connection à la base de données
        InitBDTable.connection();
        
        
        //Démarrage des intefaces graphiques
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // Supprimé, à refaire
                ApplicationCapteurs capts = new ApplicationCapteurs();
                capts.setVisible(true);
                AppClient client = new AppClient();
                
                 client.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                 //Fermeture de la base de données
                    InitBDTable.fermer();
                   }
                      });
                
                client.setVisible(true);
            }
        });
    
    InitBDTable.statement("SELECT * FROM RUCHES;");
    }
}
