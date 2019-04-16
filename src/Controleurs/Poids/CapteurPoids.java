/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs.Poids;
import Controleurs.Capteur;
import Controleurs.Mesure;
import static Controleurs.UniteMesure.POIDS;

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
    
}
