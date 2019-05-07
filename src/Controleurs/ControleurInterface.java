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
    private Affichage affichage;
    public ControleurInterface(){
        affichage = new Affichage();
    }

    public void creerNouvelleRuche(){
        CreateurRuche cg = new CreateurRuche();
        cg.creerRuche();
    }

    public String[] afficheSQLRuche(String currentRucheId){
        String[] textToSet = {"", "", "", "", ""};
        textToSet[0] = affichage.SQLRuche("couleurReine", "idRuche=" + currentRucheId);
        textToSet[1] = affichage.SQLRuche("raceReine", "idRuche=" + currentRucheId);
        textToSet[2] = affichage.SQLRuche("AgeReine", "idRuche=" + currentRucheId);
        textToSet[3] = "Nom Ruche : " + affichage.SQLRuche("nomRuche");     
        textToSet[4] = affichage.SQLRuche("nomRuche", "idRuche=" + currentRucheId);
        return textToSet;
    }
    
    public String[] afficheSQLHausse(String currentRucheId, String currentHausseId){
        String[] textToSet = {"", "", ""};
        textToSet[0] = affichage.SQLHausses("Hausse.couleur", "CompositionRuche.idRuche="
                      + currentRucheId + " AND Hausse.idMateriel = " + currentHausseId);
        textToSet[1] = affichage.SQLHausses("Hausse.numeroHausse", "CompositionRuche.idRuche="
                      + currentRucheId + " AND Hausse.idMateriel = " + currentHausseId);
        textToSet[2] = affichage.SQLHausses("Hausse.materiau", "CompositionRuche.idRuche="
                      + currentRucheId + " AND Hausse.idMateriel = " + currentHausseId);
        return textToSet; 
    }
    
    public void supprimerRuche(String currentRucheId){
        // Destruction de la ruche
        Ruche.dissocier(currentRucheId);
    }

    public void creerNouvelleHausse(){
        CreateurHausse.creer();
    }

    public void associerNouvelleHausse(){
        CreateurHausse.associer();
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



    public void majCapteurTemperature(JLabel capteurAssociéText, JComboBox<String> capteurTemperatureAssocieCombo, JButton associerCapteurTemperatureBouton, JButton dissocierCapteurTemperatureBouton, String currentHausseId) {
        Affichage affichage = new Affichage();
         capteurTemperatureAssocieCombo.removeAllItems();
        // Note : on est sûr que c'est un capteur de température car associé à un cadre
        String captAssocie = affichage.SQLEmplacementCapteur("idCapteur", "idMateriel = " + currentHausseId );
        if (captAssocie.length() > 0){
            capteurAssociéText.setText("Capteur associé :");
             capteurTemperatureAssocieCombo.addItem(captAssocie);
            associerCapteurTemperatureBouton.setVisible(false);
            dissocierCapteurTemperatureBouton.setVisible(true);
        }

        // On affiche les capteurs libres pour les associer
        else {
            String captLibres = affichage.SQLCapteurLibre( "idCapteur", " type = 'temperature' AND idCapteur");
            capteurAssociéText.setText("Capteur associable :");
            for (String s : captLibres.split(" ")){
                 capteurTemperatureAssocieCombo.addItem(s);
            }
            associerCapteurTemperatureBouton.setVisible(true);
            dissocierCapteurTemperatureBouton.setVisible(false);
        }
    }

    public void supprimerMateriel(int currentMaterielID) {
        Destruction dest = new Destruction();
        dest.SQLMateriau("idMateriel="+currentMaterielID);
    }

    public String[] afficheSQLCadre(String currentCadreId) {
        String[] textToSet = {"", "", ""};
        textToSet[0] = affichage.SQLCadre("etat", "idMateriel = " + currentCadreId);
        textToSet[1] = affichage.SQLCadre("materiau", "idMateriel = " + currentCadreId);
        textToSet[2] = affichage.SQLCadre("contenu", "idMateriel = " + currentCadreId);
        return textToSet;
    }

}
