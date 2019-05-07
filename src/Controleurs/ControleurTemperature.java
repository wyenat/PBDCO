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
public class ControleurTemperature {

    public ControleurTemperature() {
    }
    
    
    
    public void controleTemperature(){
        String idHausse = "SELECT Hausse.idMateriel, Mesure.VALEUR FROM HAUSSE"
                + " JOIN CompositionHausse ON hausse.idMateriel=CompositionHausse.IDMATERIELHAUSSE"
                + " JOIN EmplacementCapteur ON CompositionHausse.idMaterielCadre=EmplacementCapteur.idMateriel "
                + "JOIN Mesure ON EmplacementCapteur.IDCAPTEUR=Mesure.IDCAPTEUR "
                + "JOIN Capteur ON Mesure.IDCAPTEUR=Capteur.idCapteur "
                + "JOIN (SELECT idCapteur, MAX(horodatage)AS maxDate FROM MESURE GROUP BY idCapteur) dates "
                + "ON Mesure.horodatage = dates.maxdate AND dates.idCapteur = EmplacementCapteur.IDCAPTEUR "
                + "WHERE Capteur.type='temperature' "
                + "GROUP BY Hausse.IDMATERIEL, Mesure.valeur "
                + "HAVING (Mesure.VALEUR >= 39 OR Mesure.VALEUR <= 35)";
       String res = BDTable.requeteDouble(idHausse);
       String[] separated = res.split(" ");
      if (res!=null && res!=""){
            String ruche = "";
            String numeros = "";
            for (int i =0; i<separated.length; i+=2){
                String idRuche =  "SELECT nomRuche, Ruche.idRuche FROM Ruche"
                        + " JOIN CompositionRuche ON compositionRuche.IDRUCHE=Ruche.IDRUCHE "
                        + "WHERE compositionRuche.idMateriel= " + separated[i];
                String id = BDTable.requete(idRuche);
                String[] idSepare = id.split(" ");
                try{
                    String hausse =  "SELECT numeroHausse FROM Hausse WHERE idMateriel= " + separated[i];
                    String numero = BDTable.requete(hausse);
                    numeros += numero + " ";
                    ruche += idSepare[0] + " (id: " + idSepare[1] + ") ";
                    
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e){
                    System.out.println("Il y a des problemes dans la BD");
                }
                
            }
            Alerte.main("La ou les ruche(s) " + ruche + "a/ont besoin d'une intervention à cause de leur poids trop faible respectivement sur la ou leur(s) hausse(s) numéro " + numeros);
       }
      }
       
        
}

        
     
