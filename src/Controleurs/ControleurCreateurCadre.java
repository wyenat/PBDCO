/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Ruche.Cadre;
import Ruche.Contenu;
import Ruche.Etat;
import Ruche.Materiau;
/**
 *
 * @author nodelant
 */
public class ControleurCreateurCadre {

    public ControleurCreateurCadre() {
    }
    
    public void creerCadre(Contenu cont, Etat etat, Materiau mat){
        Cadre cadre = new Cadre(cont, etat, mat);
        cadre.creer();
    }
}
