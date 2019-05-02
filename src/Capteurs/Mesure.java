/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capteurs;

/**
 * Cette classe regroupe les mesures prisent par les capteurs
 */
public class Mesure {
    private int val;
    private Horodateur horodateur;
    private UniteMesure uniteMesure;

    public Mesure(int val, UniteMesure uniteMesure) {
        this.val = val;
        this.uniteMesure = uniteMesure;
        this.horodateur= new Horodateur();
    }

    public int getVal() {
        return val;
    }

    public String getHorodatage() {
        return horodateur.getDate().toString();
    }

    public UniteMesure getUniteMesure() {
        return uniteMesure;
    }
    
}


