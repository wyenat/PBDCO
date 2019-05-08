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
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLHausse(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLHausse(String req, String cond) {
        req = "SELECT " + req + " FROM HAUSSE" + " WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLMesure(String req, String cond) {
        req = "SELECT " + req + " FROM Mesure" + " WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLMesure(String req) {
        req = "SELECT " + req + " FROM Mesure";
        String result = BDTable.requete(req);
        return result;
    }

    public String SQLUniteCapteur(String req) {
        req = "SELECT " + req + " FROM UniteCapteur";
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
         req = "SELECT " + req +
                " FROM COMPOSITIONHAUSSE";
        return BDTable.requete(req);
    }

    @Override
    public String SQLCompositionHausse(String req, String cond) {
        req = "SELECT " + req +
                " FROM COMPOSITIONHAUSSE WHERE " + cond;
        return BDTable.requete(req);
    }

    public String SQLMateriauLibre(String req, String mat) {
            req = "SELECT " + req + 
                " FROM " + mat +" WHERE idMateriel NOT IN \n" +
"                (SELECT idMateriel " +
"                FROM COMPOSITIONRUCHE)";
            String result = BDTable.requete(req);
            return result;
    }

    public String SQLHausses(String req, String cond) {
        req = "SELECT DISTINCT " + req + " FROM HAUSSE " 
               + " JOIN COMPOSITIONRUCHE ON COMPOSITIONRUCHE.IDMATERIEL = HAUSSE.IDMATERIEL "
               + " WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCompositionRuche(String req) {
          req = "SELECT " + req +
                " FROM COMPOSITIONRUCHE";
        return BDTable.requete(req);
    }

    @Override
    public String SQLCompositionRuche(String req, String cond) {
         req = "SELECT " + req +
                " FROM COMPOSITIONRUCHE WHERE " + cond;
        return BDTable.requete(req);
    }

    @Override
    public String SQLCapteurs(String req, String cond) {
        req = "SELECT " + req + " FROM Capteur WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCapteurs(String req) {
         req = "SELECT " + req + " FROM Capteur";
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLCapteurLibre(String req, String cond) {
         req = "SELECT " + req +  " FROM CAPTEUR WHERE " + cond + " NOT IN " 
                + "(SELECT " + req 
                + " FROM EMPLACEMENTCAPTEUR)";
            String result = BDTable.requete(req);
            return result;
    }

    @Override
    public String SQLEmplacementCapteur(String req, String cond) {
         req = "SELECT " + req + " FROM EMPLACEMENTCAPTEUR WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }

    @Override
    public String SQLEmplacementCapteur(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLCapteursJoin(String req, String cond) {
        req = "SELECT distinct Capteur." + req + " FROM EMPLACEMENTCAPTEUR" +
        " JOIN Capteur ON EmplacementCapteur.idCapteur=Capteur.idCapteur " +
        "WHERE " + cond;
        String result = BDTable.requete(req);
        return result;
    }
}
