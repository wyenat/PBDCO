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
        Ruche.dissocier(currentRucheId);
    }
    
    public void creerNouvelleHausse(){
        CreateurHausse ch = new CreateurHausse();
        ch.creer();
    }
    
    public void creerNouveauMateriau(String materiel, Materiau materiau){
        // Fonction pour ajouter du matériel
        Materiel mat;
        switch (materiel){
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

    public void creerNouveauCadre() {
        CreateurCadre cc = new CreateurCadre();
        cc.creer();
    }

    public void registerIntervention(Boolean seconde, String intervention, JComboBox<String> detailCombo1, JComboBox<String> detailCombo, String currentRucheId, String currentHausseId) {
        Modification modif = new Modification();
        switch(intervention){
            case "Récolte d'une hausse":
                String idPrems = detailCombo.getSelectedItem().toString().split(" ")[0];
                String valPrems = detailCombo.getSelectedItem().toString().split(" ")[3];
                if (seconde){
                    String idDeuz = detailCombo1.getSelectedItem().toString().split(" ")[0];
                    String valDeuz = detailCombo1.getSelectedItem().toString().split(" ")[3];
                    if (valPrems.equals(valDeuz)){
                        modif.SQLCompositionRuche("", idPrems);
                        modif.SQLCompositionRuche(currentRucheId, idDeuz);
                    } else {
                        Erreur.main("Il faut remplacer une hausse " + valPrems
                                   + " et pas par une " + valDeuz + " ! "
                                    + "\n ( La hausse fait partie du corps de ruche )");
                    }
                } else {
                    modif.SQLCompositionRuche("", idPrems);
                }
                break;
            case "Pose d'une nouvelle hausse de récolte":
                idPrems = detailCombo.getSelectedItem().toString().split(" ")[0];
                modif.SQLCompositionRuche("", idPrems);
                if (seconde){
                    String idDeuz = detailCombo1.getSelectedItem().toString().split(" ")[0];
                    modif.SQLCompositionRuche(currentRucheId, idDeuz);
                }
                break;
            case "Insertion d'un cadre dans une hausse":
                String premierCadre = detailCombo.getSelectedItem().toString();
                String secondCadre = detailCombo1.getSelectedItem().toString();
                modif.SQLCompositionHausse("idMaterielHausse = null",
                        "idMaterielCadre = '" + secondCadre.split(" ")[0] +"'");
                modif.SQLCompositionHausse("idMaterielHausse = '" + currentHausseId + "'",
                        "idMaterielCadre = '" + premierCadre.split(" ")[0] +"'");
                break;
            case "Extraction d'un cadre d'une hausse":
                premierCadre = detailCombo.getSelectedItem().toString();
                secondCadre = detailCombo1.getSelectedItem().toString();
                modif.SQLCompositionHausse("idMaterielHausse = null",
                        "idMaterielCadre = '" + premierCadre.split(" ")[0] +"'");
                modif.SQLCompositionHausse("idMaterielHausse = '" + currentHausseId + "'",
                        "idMaterielCadre = '" + secondCadre.split(" ")[0] +"'");
            break;
        }
    }
}
