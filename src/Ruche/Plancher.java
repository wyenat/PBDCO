package Ruche;

import javax.swing.JList;

public class Plancher extends Materiel {

    public static String getListe() {
        return Materiel.getListe("Plancher");
    }

    
    public Plancher(Materiau materiau) {
        super(materiau);
    }
    
    
}
