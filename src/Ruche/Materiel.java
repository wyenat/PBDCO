package Ruche;

import InterfaceGraphique.Erreur;
import SQL.Affichage;
import SQL.Création;
import javax.swing.JList;

public class Materiel {
    
    protected int idMateriel;
    protected Materiau materiau;

    public Materiel(Materiau materiau) {
        String req = "idMateriel";
        Affichage aff = new Affichage();
        String resultat = aff.SQLMateriau(req);
        int candidat = -1;
        if (resultat!=""){
            for (String potentiel : resultat.split(" ")){
                if (Integer.parseInt(potentiel) > candidat){
                    candidat = Integer.parseInt(potentiel);
                }
            }
        }
        this.idMateriel = candidat + 1;
        this.materiau = materiau;
    }

    public int getIdMateriel() {
        return idMateriel;
    }

    public Materiau getMateriau() {
        return this.materiau;
    }

    public void create() {
        Création crea = new Création();
        crea.SQLMateriau("" + idMateriel +"," + "'" +this.materiau.toString()+"'", this.getClass());
    }

    /**
     * Renvoie la liste des matériaux disponibles
     * @param mat le String qui indique le matériel disponible
     * @return liste
     */
    protected static String getListe(String mat) {
         String req = "idMateriel, materiau";
        Affichage aff = new Affichage();
        String res = "" + aff.SQLMateriauLibre(req, mat);
        String resultat= "" ;
        int i = 1;
        for (String s : res.split(" ")){
            if (i%2 != 0){
                resultat+=s +" ";
            } else {
                resultat+=s  + ",";
            }
            i++;
        }
        resultat = resultat.substring(0, resultat.length() - 1);
        return resultat;
    }
    
    public static boolean verification(String type, JList<String> list){
        int n = list.getSelectedIndices().length;
        if (n != 1){
             Erreur.main("Il faut 1 " + type + "! \n"
                 + n + "ont été séléctionnés !");
             return false;
        }
        return true;
    }
}
