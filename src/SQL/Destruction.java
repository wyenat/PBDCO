/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

/**
 * Gère la destruction de lignes
 */
public class Destruction implements FabriqueSQL{

    @Override
    /**
     * @param cond la condition de destruction
     */
    public String SQLRuche(String cond) {
        System.out.println("YOYOYO");
       cond = "DELETE FROM RUCHES WHERE (" + cond + ")";
       System.out.println(cond);
       BDTable.requete(cond);
       
       return "Deleted";
    }

    @Override
    public void SQLHausse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SQLRuche(String req, String cond) {
        throw new UnsupportedOperationException("Pas de conditions en création !"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
