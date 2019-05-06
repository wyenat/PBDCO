/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import Ruche.Materiel;

/**
 * Gère la destruction de lignes
 */
public class Destruction implements FabriqueSQL{

    @Override
    /**
     * @param cond la condition de destruction
     */
    public String SQLRuche(String cond) {
       cond = "DELETE FROM RUCHE WHERE (" + cond + ")";
       BDTable.requete(cond);
       
       return "Deleted";
    }


    @Override
    public String SQLRuche(String req, String cond) {
        throw new UnsupportedOperationException("Pas de conditions en création !"); //To change body of generated methods, choose Tools | Templates.
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
    public String SQLMateriau(String cond) {
        cond = "DELETE FROM "+"TOIT T JOIN COUVERCLE C ON T.idMATERIEL=C.idMATERIEL "+" WHERE (" + cond+ ")";
        BDTable.requete(cond);
       
       return "Deleted";
    }

    @Override
    public String SQLMateriau(String cond, Class<? extends Materiel> aClass) {
//        String table = aClass.getName().replace("Ruche.", "");
//        cond = "DELETE FROM "+table+" WHERE (" + cond + ")";
//        BDTable.requete(cond);
//       
       return "Deleted";
    }

    @Override
    public String SQLCadre(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCadre(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCompositionHausse(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCompositionHausse(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCompositionRuche(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCompositionRuche(String req, String cond) {
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
    
}
