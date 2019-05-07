/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capteurs.Poids;
import Capteurs.Capteur;
import Capteurs.Mesure;
import static Capteurs.UniteMesure.POIDS;
import SQL.Création;

/**
 *
 * @author matterv
 */
public class CapteurPoids extends Capteur {

    public CapteurPoids() {
        super();
    }
    
    /**
     * 
     * @param val est la mesure enregistrée
     */
    public void EnregistrerMesure(int val){
        Mesure mesure = new Mesure(val, POIDS);
        EnregistrerMesure(mesure);
    }

    public void creer() {
        Création crea = new Création();
        crea.SQLCapteurs("'" + this.idCapteur + "', 'poids'");
    }
}
