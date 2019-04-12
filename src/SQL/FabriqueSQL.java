/*
 * Ce package a pour but de centraliser les appels à la base de donnée.
 */
package SQL;

/**
 *
 * @author matterv
 */

/**
 * Implémente les requêtes SQL. 
 * Il n'est pas possible de créer des requêtes SQL sans passer par cette Fabrique
 */
public interface FabriqueSQL {
    
    /**
     * Gère les requêtes SQL liées à la ruche
     * @param req est tous les identifiants qu'on veut récuperer de la table
     * RUCHES
     * @return le résultat de la requête SQL
     */
    public String SQLRuche(String req);
    
    /**
     * Gère les requêtes SQL liées à la ruche
     * @param req est tous les identifiants qu'on veut récuperer de la table
     * RUCHES 
     * @param cond est les conditions qu'on veut
     * @return
     */
    public String SQLRuche(String req, String cond);
    
    /**
     * Gère les requêtes SQL liées à la hausse
     */
    public void SQLHausse();
}
