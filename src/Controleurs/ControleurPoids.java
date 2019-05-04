/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import InterfaceGraphique.*;
import SQL.BDTable;



/**
 * Un controlleur de Poids :)
 */
public class ControleurPoids {

    public ControleurPoids() {
    }
    
    
    
    public void controlePoids(){
        String idHausse ="SELECT Hausse.idMateriel FROM HAUSSE"
        +" JOIN CompositionHausse ON hausse.idMateriel=CompositionHausse.IDMATERIELHAUSSE"
        +" JOIN EmplacementCapteur ON CompositionHausse.idMaterielCadre=EmplacementCapteur.idMateriel"
        +" JOIN Mesure ON EmplacementCapteur.IDCAPTEUR=Mesure.IDCAPTEUR"
        +" JOIN Capteur ON Mesure.IDCAPTEUR=Capteur.idCapteur"
        +" JOIN (SELECT idCapteur, MAX(horodatage)AS maxDate FROM MESURE GROUP BY idCapteur) dates"
        +" ON Mesure.horodatage = dates.maxdate AND dates.idCapteur = EmplacementCapteur.IDCAPTEUR"
        +" WHERE Capteur.type='poids'"
        +" GROUP BY Hausse.IDMATERIEL"
        +" HAVING AVG(Mesure.VALEUR)<1.5";
        
        
        
       String res = BDTable.requete(idHausse);
       String[] separated = res.split(" ");
       
       
       
       System.out.println("ALORS "+separated.length);
       if (res!="" && res!=null){
            String ruche = "";
            String numeros = "";
            for (int i =0; i<separated.length; i++){
                
                String idRuche =  "SELECT nomRuche FROM Ruche"
                        + " JOIN CompositionRuche ON compositionRuche.IDRUCHE=Ruche.IDRUCHE "
                        + "WHERE compositionRuche.idMateriel= " + separated[i];
                System.out.println(separated[i]);
                String id = BDTable.requete(idRuche);
                ruche += id + " ";
                
                String hausse =  "SELECT numeroHausse FROM Hausse WHERE idMateriel= " + separated[i];
                String numero = BDTable.requete(hausse);
                numeros += numero + " ";
                
            }
            Alerte.main("L.a.es ruche.s " + ruche + "a.ont besoin d'une intervention à cause de leur poids trop faible respectivement sur leur.s hausse.s numéro " + numeros);
       }
       
        
    }

        
     
}
