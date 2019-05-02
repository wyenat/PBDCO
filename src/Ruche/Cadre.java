package Ruche;

import SQL.Affichage;
import SQL.Création;



public class Cadre extends Materiel {

    /**
     * Permet d'afficher une liste qui résume les cadres disponibles
     */
    public static String getListe() {
        //Récupère les cadres
        String req = "idMateriel, materiau ," + "etat, " + "contenu";
        Affichage aff = new Affichage();
        String res = "" + aff.SQLCadre(req, "idMaterielHausse IS NULL");
        //On remplace tous les 3 espaces par une virgule
        String resultat= "" ;
        int i = 1;
        for (String s : res.split(" ")){
            if (i%4 != 0){
                resultat+=s +" ";
            } else {
                resultat+=s  + ",";
            }
            i++;
        }
        resultat = resultat.substring(0, resultat.length() - 1);
        System.out.println("Résultat = " + resultat);
        return resultat;
    }
    
    private Etat etat;
    private Contenu contenu;    

    public Cadre(Contenu cont, Etat etat, Materiau mat) {
        
        super(mat);
        assert(mat == Materiau.BOIS || mat == Materiau.PLASTIQUE);
        this.contenu = cont;
        this.etat = etat;
    }

    public Etat getEtat() {
        return etat;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public void creer() {
        String req = "" + this.idMateriel + ", '" 
                + this.materiau.toString() +  "', '"
                + this.getEtat().toString() + "', '" 
                + this.getContenu().toString() + "'";
        Création crea = new Création();
        crea.SQLCadre(req);
        req = "" + this.idMateriel + ", null";
        crea.SQLCompositionHausse(req);
        
    }
    
    
    
}
