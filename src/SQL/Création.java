/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

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

}
