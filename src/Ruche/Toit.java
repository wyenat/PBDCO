/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ruche;

import SQL.Affichage;
import SQL.Création;
import javax.swing.JList;

/**
 *
 */
public class Toit extends Materiel { 
    
    public Toit(Materiau materiau) {
        super(materiau);
    }
    
    public static String getListe() {
        return Materiel.getListe("TOIT");
    }
    
}
