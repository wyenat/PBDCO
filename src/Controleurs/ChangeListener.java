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
   private BDTable BD;
   
   // Constructeur

    public ChangeListener(BDTable BD) {
        this.BD = BD;
    }
   
   
   // Méthode obligatoire pour réagir à une modification de la BD
   public void  onDatabaseChangeNotification(DatabaseChangeEvent e)
   {
        BD.refresh(e);           // N'importe quelle méthde peut être appelée
        
    }
}
