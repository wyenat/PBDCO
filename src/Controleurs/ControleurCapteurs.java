/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import InterfaceGraphique.*;
import SQL.*;
import java.util.Random;



/**
 * Un controlleur de Poids :)
 */
public class ControleurCapteurs {
    
    static int horodatage = 0;
    
    public ControleurCapteurs() {
    }
    
    
    
    public void ajouterMesures(){
           String capteursPoids = "SELECT idCapteurs FROM CAPTEURS WHERE type = 'poids'";
           String capteursTemperature = "SELECT idCapteurs FROM CAPTEURS WHERE type = 'temperature'";
           capteursPoids = BDTable.requete(capteursPoids);
           capteursTemperature = BDTable.requete(capteursTemperature);
           Création crea = new Création();
           Random rand = new Random();
           
           for (int i = 0; i<capteursPoids.split(" ").length; i++){
                int value = (int) (rand.nextGaussian()+(1.5));
                crea.SQLMesure(""+capteursPoids.split(" ")[i] + ", " + horodatage + ", "+value);
           }
           for (int i = 0; i<capteursTemperature.split(" ").length; i++){
                int value = (int) (rand.nextGaussian()+37);
                crea.SQLMesure(""+capteursTemperature.split(" ")[i] + ", " + horodatage + ", "+value);
           }
           horodatage++;
    }

        
     
}
