/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Capteurs.Poids.CapteurPoids;
import Capteurs.Temperature.CapteurTemperature;
import InterfaceGraphique.*;
import SQL.*;
import java.util.Random;



/**
 * Un controlleur de Poids :)
 */
public class ControleurCapteurs {
    
    static int horodatage = 0;
    
    public ControleurCapteurs() {
        Affichage aff = new Affichage();
        try{
            String horo = aff.SQLMesure("MAX(horodatage)");
            horodatage = Integer.parseInt(horo.replace(" ", "")) +1;
        }
        catch (java.lang.NullPointerException e){
            horodatage = 0;
        }
    }

    public void ajouterMesures(){
           Affichage aff = new Affichage();
           String capteursPoids = "idCapteur";
           capteursPoids = aff.SQLCapteurs(capteursPoids, " type = 'poids'");
           String capteursTemperature = "idCapteur";
           capteursTemperature = aff.SQLCapteurs(capteursTemperature, " type = 'temperature'");
           
           Création crea = new Création();
           Random rand = new Random();
           
           for (int i = 0; i<capteursPoids.split(" ").length; i++){
                int value = (int) (rand.nextGaussian()+(1.5));
                crea.SQLMesure(capteursPoids.split(" ")[i].toString() + ", '" + horodatage + "', "+value);
           }
           for (int i = 0; i<capteursTemperature.split(" ").length; i++){
                int value = (int) (rand.nextGaussian()+37);
                crea.SQLMesure(""+capteursTemperature.split(" ")[i] + ", '" + horodatage + "', "+value);
           }
           horodatage++;
    }

    public void associerCapteurTemperature(int idCapt, String currentHausseId) {
        Création crea = new Création();
        crea.SQLEmplacementCapteur(idCapt + ", " + currentHausseId);
    }

    public void dissocierCapteurTemperature(String currentHausseId) {
        Destruction destru = new Destruction();
        destru.SQLEmplacementCapteur("idMateriel = " +currentHausseId);
    }

    public void associerCapteurPoids(int idCapt, String currentCadreId) {
        Création crea = new Création();
        crea.SQLEmplacementCapteur(idCapt + ", " + currentCadreId);
    }

    public void dissocierCapteurPoids(String currentCadreId) {
        Destruction destru = new Destruction();
        destru.SQLEmplacementCapteur("idMateriel = " +currentCadreId);
    }

    public void creerCapteurPoids(){
        CapteurPoids cp = new CapteurPoids();
        cp.creer();
    }     

    public void creerCapteurTemperature() {
        CapteurTemperature ct = new CapteurTemperature();
        ct.creer();
    }
}
