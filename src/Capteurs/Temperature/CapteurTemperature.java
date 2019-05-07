/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capteurs.Temperature;

import Capteurs.Capteur;
import SQL.Création;

public class CapteurTemperature extends Capteur {

    public void creer() {
        Création crea = new Création();
        crea.SQLCapteurs("'" + this.idCapteur + "', 'temperature'");
    }
    
}
