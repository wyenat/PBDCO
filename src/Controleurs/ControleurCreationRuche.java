/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Ruche.Ruche;
import javax.swing.JList;

/**
 *
 * @author nodelant
 */
public class ControleurCreationRuche {

    public void creerRuche(String race, int age, String couleur, String nomRuche, JList<String> listC, JList<String> listP, JList<String> listT, JList<String> listH) {
        Ruche r = new Ruche();
        if (r.verifier(race, age, nomRuche)){
            r.creer(race, age, couleur, nomRuche, listC, listP, listT, listH);
        }
    }
}
