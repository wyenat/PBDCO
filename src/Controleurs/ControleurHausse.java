/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import SQL.Affichage;

/**
 *
 * @author matterv
 */
public class ControleurHausse {

    public ControleurHausse() {
    }
    
    public String[] initVueHausse(String mode, String cond){
        if (mode.equals("Sans cadre")){
            String req = "Hausse.idMateriel, materiau, couleur, numeroHausse";
            Affichage aff = new Affichage();
            String res = aff.SQLHausses(req, cond);
            String resultat= "" ;
                int i = 1;
                for (String s : res.split(" ")){
                    if (i%4 != 0){
                        resultat+=s +" ";
                    } else {
                        resultat+=s  + ",";
                    }
                    i++;
                }
            resultat = resultat.substring(0, resultat.length() - 1);
            return resultat.split(",");
        }
        if (mode.equals("Avec cadre")){
            String req = "Hausse.idMateriel, materiau, couleur, numeroHausse";
            Affichage aff = new Affichage();
            String res = aff.SQLHausses(req, cond);
            String resultat= "" ;
                int i = 1;
                for (String s : res.split(" ")){
                    if (i%4 != 0){
                        resultat+=s +" ";
                    } else {
                        resultat+=s  + ",";
                    }
                    i++;
                }
            resultat = resultat.substring(0, resultat.length() - 1);
            return resultat.split(",");
        }
        return null;
    }
}
