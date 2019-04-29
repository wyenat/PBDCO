/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import Ruche.Materiel;

/**
 * Cette classe sert à fabriquer des requêtes SQL utilisées dans l'affichage 
 * des interfaces graphiques
 */
public class Affichage implements FabriqueSQL{

    @Override
    public String SQLRuche(String req) {
        req = "SELECT " + req + " FROM RUCHE";
        String result = BDTable.requete(req);
        return result;
    }


    @Override
    public String SQLRuche(String req, String cond) {
        req = "SELECT " + req + " FROM RUCHE" + " WHERE " + cond;
        //System.out.println("REQ :"+ req);
        String result = BDTable.requete(req);
        return result;
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

    public String SQLUniteCapteur(String req) {
        req = "SELECT " + req + " FROM UniteCapteur";
        //System.out.println("REQ :"+ req);
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLUniteCapteur(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLMateriau(String req) {
        req = "SELECT " + req + " FROM PLANCHER "
                + "UNION SELECT " + req + " FROM COUVERCLE "
                + "UNION SELECT " + req + " FROM TOIT "
                + "UNION SELECT " + req + " FROM CADRE "
                + "UNION SELECT " + req + " FROM HAUSSE";
        String result = BDTable.requete(req);
        return result;
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
        req = "SELECT " + req + " FROM CADRE JOIN COMPOSITIONHAUSSE " +
              "ON IDMATERIEL = IDMATERIELCADRE" +
               " WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCompositionHausse(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCompositionHausse(String req, String cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
