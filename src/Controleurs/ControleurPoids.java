/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import InterfaceGraphique.Erreur;
import SQL.BDTable;



/**
 * Un controlleur de Poids :)
 */
public class ControleurPoids {

    public ControleurPoids() {
    }
    
    
    
    public void controlePoids(){
        
        String hausses =  "SELECT Hausse.IDMATERIEL, AVG(Mesure.VALEUR) as moy FROM HAUSSE"
                + " JOIN CompositionHausse ON hausse.idMateriel=CompositionHausse.IDMATERIELHAUSSE"
                + " JOIN EmplacementCapteur ON CompositionHausse.idMaterielCadre=EmplacementCapteur.idMateriel"
                + " JOIN Mesure ON EmplacementCapteur.IDCAPTEUR=Mesure.IDCAPTEUR"
                + " JOIN Capteur ON Mesure.IDCAPTEUR=Capteur.idCapteur"
                + " JOIN (SELECT idCapteur, MAX(horodatage)AS maxDate FROM MESURE GROUP BY idCapteur) dates "
                + " ON Mesure.horodatage = dates.maxdate AND dates.idCapteur = EmplacementCapteur.IDCAPTEUR"
                + " WHERE Capteur.type='poids'"
                + " GROUP BY Hausse.IDMATERIEL";
        
       String res = BDTable.requeteDouble(hausses);
       String[] separated = res.split(" ");
       res ="";
       for (int i = 1; i+1<=separated.length; i+=2){
           if (Float.parseFloat(separated[i])>1.5){
               res+=separated[i-1];
           }
       }
       
       
       
       if (res!=null){
            String ruche = "";
            String numeros = "";
            for (int i =0; i<res.split(" ").length; i++){
                String idRuche =  "SELECT idRuche FROM compositionRuche WHERE idMateriel= " + res.split(" ")[i];
                String id = BDTable.requete(idRuche);
                ruche += id + " ";
                
                String hausse =  "SELECT numeroHausse FROM Hausse WHERE idMateriel= " + res.split(" ")[i];
                String numero = BDTable.requete(hausse);
                numeros += numero + " ";
                
            }
        
            Erreur.main("Les ruches " + ruche + " ont besoin d'une intervention respectivement sur leurs hausses numéro " + numeros);
       }
       
        
    }

        
     
}
