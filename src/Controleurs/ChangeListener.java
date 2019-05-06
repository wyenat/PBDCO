/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import SQL.BDTable;
import oracle.jdbc.dcn.*;

/**
 *
 * @author debeaupe
 */
public class ChangeListener implements DatabaseChangeListener
{
   // Quel objet doit être notifié de modifications de la BD ?
   private BDSurveille BD;
   private ControleurPoids poids;
   private ControleurTemperature temperature;
   
   
   // Constructeur

    public ChangeListener(BDSurveille BD) {
        this.BD = BD;
        poids = new ControleurPoids();
        temperature = new ControleurTemperature();
    }
   
   
   // Méthode obligatoire pour réagir à une modification de la BD
   public void  onDatabaseChangeNotification(DatabaseChangeEvent e)
   {
        BD.refresh(e);           // N'importe quelle méthde peut être appelée
        poids.controlePoids();
        temperature.controleTemperature();
    }
}
