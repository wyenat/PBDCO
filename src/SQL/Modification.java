/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import Ruche.Materiel;

/**
 *
 * @author matterv
 */
public class Modification implements FabriqueSQL{

    @Override
    public String SQLRuche(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLRuche(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLHausse(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String SQLMateriau(String req, Class<? extends Materiel> aClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCadre(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCadre(String req, String cond) {
        String result = "UPDATE CADRE SET " + req + " WHERE IDMATERIEL = '" + cond +"'";
        
        BDTable.requete(result);
        return "";
    }

    @Override
    public String SQLCompositionHausse(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
     /**
      * @param req est les paramètres qu'on veut remplacer
      */
    public String SQLCompositionHausse(String req, String cond) {
        String result = "UPDATE COMPOSITIONHAUSSE SET " 
                + req + " WHERE " + cond;
        BDTable.requete(result);
        return "";
    }

    public String SQLCompositionRuche(String req, String cond) {
         String result = "UPDATE COMPOSITIONRUCHE SET IDRUCHE = '" 
                + req + "' WHERE " + "IDMATERIEL = '" + cond +"'";
        BDTable.requete(result);
        return "";
    }

    @Override
    public String SQLCompositionRuche(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCapteurs(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCapteurs(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCapteurLibre(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLEmplacementCapteur(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLEmplacementCapteur(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCapteursJoin(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
