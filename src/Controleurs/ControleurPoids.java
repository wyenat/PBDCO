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
        
       String res = BDTable.requete(hausses);
       System.out.println(res);
       
       if (res!=null){
            Erreur.main("les Ruches " + res + " ont besoin d'une intervention");
       }
       
        
    }

        
     
}
