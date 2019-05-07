/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capteurs;

import SQL.Affichage;
import SQL.Création;

/**
 * Un capteur :)
 */
public abstract class Capteur {
    protected int idCapteur;
    
    public Capteur(){
        InitIdCapteur();
    }
        
        

    private void InitIdCapteur() {
        String req = "idCapteur";
        Affichage aff = new Affichage();
        String resultat = aff.SQLCapteurs(req);
        int candidat = -1;
        if (resultat!=""){
            for (String potentiel : resultat.split(" ")){
                if (Integer.parseInt(potentiel) > candidat){
                    candidat = Integer.parseInt(potentiel);
                }
            }
        }
        this.idCapteur = candidat + 1;
    }
    
    /**
     * Enregistre une mesure dans la base de données
     * @param mesure Mesure à enregistrer
     */
    public void EnregistrerMesure(Mesure mesure){
        Création crea = new Création();
        String req = "";
        // CapteurId
        req += this.idCapteur;
        //horodatage
        req += "," + mesure.getHorodatage();
        //valeur
        req += "," + mesure.getVal();
        crea.SQLMesure(req);
    }
}
