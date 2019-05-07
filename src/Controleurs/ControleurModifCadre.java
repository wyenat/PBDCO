/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;
import InterfaceGraphique.Erreur;
import Ruche.Contenu;
import Ruche.Etat;
import SQL.Affichage;
import SQL.Modification;
/**
 *
 * @author nodelant
 */
public class ControleurModifCadre {
    private Affichage affichage = new Affichage();
    
    public ControleurModifCadre() {
    }

    public String[] afficheSQLCadre(String currentCadreId) {
        String[] textToSet = {"", ""};
        textToSet[0] = affichage.SQLCadre("etat", "idMateriel = " + currentCadreId);
        textToSet[1] = affichage.SQLCadre("contenu", "idMateriel = " + currentCadreId);
        return textToSet;
    }

    public boolean confirmerModif(Etat nouveauEtat, Contenu nouveauContenu, String currentHausseId, String currentCadreId) {
        //Vérification de l'intégrité :
        boolean integre = true;
        String num = affichage.SQLHausse("numeroHausse", "idMateriel = " + currentHausseId);
        if (nouveauContenu != Contenu.VIDE){
            int nume = Integer.parseInt(num.replace(" ", ""));
            if (nume != 1 && nume != 2){
                integre = false;
            }
        }
        // Modification
        if (integre){
              Modification modif = new Modification();
              modif.SQLCadre("etat = '" + nouveauEtat + "', contenu = '" + nouveauContenu +"'", currentCadreId);
        } else {
             Erreur.main("Une hausse de numéro " + num + " ne peut pas contenir " + nouveauContenu.toString());
        }
        return(integre);
    }
    
    
    
    
    
}
