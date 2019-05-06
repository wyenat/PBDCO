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
import javax.swing.JComboBox;

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
    
    public void creerNouveauMateriau(Object omateriau){
        // Fonction pour ajouter du matériel
        Materiau materiau = (Materiau) omateriau;
        Materiel mat;
        switch ((String) omateriau){
            case "Toit":
                mat = new Toit(materiau);
                break;
            case "Couvercle":
                mat = new Couvercle(materiau);
                break;
            case "Plancher":
                mat = new Plancher(materiau);
                break;
            default:
                mat = null;
                System.out.println("Erreur");
                break;
        }
        if (mat != null) {
            mat.create();
        }
    }
}
