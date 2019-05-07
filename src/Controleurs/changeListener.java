/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import oracle.jdbc.dcn.*;

/**
 *
 * @author debeaupe
 */
class changeListener implements DatabaseChangeListener
{
   // Quel objet doit être notifié de modifications de la BD ?
   private ControleurPoids controleurPoids;
   private ControleurTemperature controleurTemperature;
   
   // Constructeur

    public changeListener(ControleurPoids controleurPoids, ControleurTemperature controleurTemperature) {
        this.controleurPoids = controleurPoids;
        this.controleurTemperature = controleurTemperature;
    }
    
   
   // Méthode obligatoire pour réagir à une modification de la BD
   public void  onDatabaseChangeNotification(DatabaseChangeEvent e)
   {
      controleurPoids.controlePoids();  
      controleurTemperature.controleTemperature();  
      
   }
}
