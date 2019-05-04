/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ruche;

import javax.swing.JList;

/**
 *
 */
public class Couvercle extends Materiel {

    public static String getListe() {
        return Materiel.getListe("COUVERCLE");
    }

    public Couvercle(Materiau materiau) {
        super(materiau);
    }
    
}
