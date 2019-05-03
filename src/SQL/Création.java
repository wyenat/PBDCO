/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import Ruche.Materiel;

/**
 *
 * Gère la création SQL
 */
public class Création implements FabriqueSQL{

    @Override
    /**
     * @param req est les valeurs rentrées, séparées par des virgules.
     * Suppose que l'ordre des colonnes est su par l'utilisateur.
     */
    public String SQLRuche(String req) {
        /*** Si jamais besoin :
         *  CREATE TABLE RUCHE (
   idRuche INT,
   raceReine VARCHAR(255),
    ageReine INT,
  couleurReine VARCHAR(255),
   nomRuche VARCHAR(255)
   );
         */
        req = "INSERT INTO RUCHE VALUES ( " + req + " )"; 
        String result = BDTable.requete(req);
        return result;
    }
    
     @Override
    public String SQLRuche(String req, String cond) {
        throw new UnsupportedOperationException("Pas de conditions en création !");
    }

    @Override
    public String SQLHausse(String req) {
        req = "INSERT INTO HAUSSE VALUES ( " + req + " )"; 
        System.out.println(req);
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLHausse(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLMesure(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLMesure(String req) {
        req = "INSERT INTO MESURE VALUES ( " + req + " )"; 
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLUniteCapteur(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLUniteCapteur(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLMateriau(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * Permet de créer une ligne de Matériau du matériau donné
     * @param string comme pour les autres
     * @param aClass permet de spécifier le type de matériel qu'on manipule
     */
    public String SQLMateriau(String req, Class<? extends Materiel> aClass) {
        String table = aClass.getName().replace("Ruche.", "");
        req = "INSERT INTO "+ table +" VALUES ( " + req + " )"; 
        System.out.println(req);
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCadre(String req) {
        req = "INSERT INTO CADRE VALUES ( " + req + " )"; 
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCadre(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCompositionHausse(String req) {
        req = "INSERT INTO COMPOSITIONHAUSSE VALUES ( " + req + " )"; 
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCompositionHausse(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String SQLCompositionRuche(String req) {
        req = "INSERT INTO COMPOSITIONRUCHE VALUES ( " + req + " )"; 
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCompositionRuche(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
