/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import InterfaceGraphique.*;
import Ruche.*;
import SQL.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author nodelant
 */
public class ControleurInterface {
    
    public ControleurInterface(){
    }
    
    public void creerNouvelleRuche(){
        CreateurRuche cg = new CreateurRuche();
        cg.creerRuche();
    }
    
    public void supprimerRuche(String currentRucheId){
        // Destruction de la ruche
        Avertissement aver = new Avertissement();
        aver.avertir();
        if (!aver.getAnswer()){
            Destruction destru = new Destruction();
            destru.SQLRuche("idRuche="+currentRucheId);
            System.out.println("Ruche d'id : " + currentRucheId + " supprimée");
        } else {
            System.out.println("Ruche non supprimée");
        }
    }
    
    public void creerNouveauMateriau(Materiau omateriau){
        // Fonction pour ajouter du matériel
        Materiau materiau = (Materiau) omateriau;
        Materiel mat;
//        if (materiau == Materiau.BOIS){
//                mat = new Toit(materiau);
//        }
//        else if(materiau == Materiau.METAL){
//                
//        }
//        else if(materiau == Materiau.PLASTIQUE)
//            case "Couvercle":
//                mat = new Couvercle(materiau);
//                break;
//            case "Plancher":
//                mat = new Plancher(materiau);
//                break;
//            default:
//                mat = null;
//                System.out.println("Erreur");
//                break;
//        }
//        if (mat != null) {
//            mat.create();
//        }
    }

    public void associerCapteurPoids(int idCapt, String currentCadreId) {
        Création crea = new Création();
        crea.SQLEmplacementCapteur(idCapt + ", " + currentCadreId);
    }

    public void majCapteurAssocie(JComboBox<String> capteurPoisdAssociéBox, String currentCadreId, JLabel textCaptPoidsAssocier, JComboBox<String> capteurPoisdAssociéBox0, JButton associerCapteurPoidsBouton, JButton dissocierCapteurPoidsBouton) {
        Affichage affichage = new Affichage();
        capteurPoisdAssociéBox.removeAllItems();
        // Note : on est sûr que c'est un capteur poids car associé à un cadre
        String captAssocie = affichage.SQLEmplacementCapteur("idCapteur", "idMateriel = " + currentCadreId );
        if (captAssocie.length() > 0){
            textCaptPoidsAssocier.setText("Capteur associé :");
            capteurPoisdAssociéBox.addItem(captAssocie);
            associerCapteurPoidsBouton.setVisible(false);
            dissocierCapteurPoidsBouton.setVisible(true);
        }
       
        // On affiche les capteurs libres pour les associer
        else { 
            String captLibres = affichage.SQLCapteurLibre( "idCapteur", " type = 'poids' AND idCapteur");
            textCaptPoidsAssocier.setText("Capteur associable :");
            for (String s : captLibres.split(" ")){
                capteurPoisdAssociéBox.addItem(s);
            }
            associerCapteurPoidsBouton.setVisible(true);
            dissocierCapteurPoidsBouton.setVisible(false);
        }
    }

    public void dissocierCapteurPoids(String currentCadreId) {
        Destruction destru = new Destruction();
        destru.SQLEmplacementCapteur("idMateriel = " +currentCadreId);
    }
}
