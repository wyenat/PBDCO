/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

/**
 *
 * @author matterv
 */

/**
 * Implémente les requêtes SQL
 * @author matterv
 */
public interface Fabrique {
    
    /**
     * Gère les requêtes SQL liées à la ruche
     */
    public void SQLRuche();
    
    /**
     * Gère les requêtes SQL liées à la hausse
     */
    public void SQLHausse();
}
