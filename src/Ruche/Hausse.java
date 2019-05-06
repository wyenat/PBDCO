package Ruche;

import InterfaceGraphique.Erreur;
import SQL.Affichage;
import SQL.Création;
import SQL.Destruction;
import SQL.Modification;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JList;

public class Hausse extends Materiel {

    /**
     * cf Cadre.getListe();
     */
    public static String getListe(String cond) {
        // Récupère les hausses
        String req = "Hausse.idMateriel, materiau, couleur, numeroHausse";
        Affichage aff = new Affichage();
        //Non assignées !
        String res = "" + aff.SQLHausses(req, cond);
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
        return resultat;
    }

    /**
     * Dissocie la hausse de sa ruche
     * @param currentHausseId 
     */
    public static void dissocier(String currentHausseId) {
        Destruction destru = new Destruction();
        // On dissocie la hausse de la ruche
        destru.SQLCompositionHausse("idMateriel = " +currentHausseId);
    }
    
    private int numeroHausse;
    private Couleur couleur;

    public Hausse(int numero, Materiau materiau, Couleur couleur) {
        super(materiau);
        this.numeroHausse = numero;
        this.couleur = couleur;
    }
    
    public boolean verifier( List<String> cadres){
        boolean correct = true;
        if (this.numeroHausse < 1){
            Erreur.main("Le numéro de la hausse doit être supérieur à 0, "
                    + "ici " + this.numeroHausse);
            correct = false;
        }
        if (cadres.size() != 10){
            Erreur.main("Une hausse est composée d'exactement 10 hausses, et "
                    + "seulement " + cadres.size() + " ont été selectionnées");
            correct = false;
        } else {
            for (String cadre : cadres){
                String contenu = cadre.split(" ")[3];
                if ( contenu.equals("LARVES") || contenu == "POLLEN" ){
                    System.out.println(contenu);
                    if (this.numeroHausse != 1 && this.numeroHausse != 2){
                        Erreur.main("Le numéro de la hausse doit être 1 ou 2 quand "
                                + "le cadre contient " + contenu + "\n Ici, le numéro"
                                + " rentré est " + this.numeroHausse);
                        correct = false;
                    break;
                    }
                }
            }
        }
        return correct;
    }
    
    public static boolean verification(String type, JList<String> list){
        HashSet<Integer> present = new HashSet<Integer>();
        int n = list.getSelectedIndices().length;
        if (n < 2){
             Erreur.main("Il faut au moins 2 " + type + "! \n"
                 + n + "ont été séléctionnés !");
             return false;
        }
        int num;
        for (String s : list.getSelectedValuesList()){
            num = Integer.parseInt(s.split(" ")[3]);
            if (!present.contains(num)){
                 present.add(num);
            } else {
                Erreur.main("Deux hausses portent le numéro " + num);
                return false;
            }
        }
        if (!present.contains(1) || !present.contains(2)){
            Erreur.main("Il n'y a pas une hausse 1 et une hausse 2 !");
            return false;
        }
        return true;
    }
    
    
    

    public int getnumeroHausse() {
        return numeroHausse;
    }

    public Couleur getCouleur() {
        return couleur;
    }


    public void creer(List<String> selection) {
        // Affectation des cadres à la hausse
        Modification modif = new Modification();
        for (String cadre : selection){
                String idCadre = cadre.split(" ")[0];
                modif.SQLCompositionHausse("" + this.idMateriel, idCadre);
        }
        
        // Création de la hausse dans la table, associée à aucune ruche
        Création crea = new Création();
        String req = "" + this.idMateriel + ", null";
        crea.SQLCompositionRuche(req);
        
        // Création de la hausse dans la table de la hausse
        req = idMateriel + ", '" + this.materiau.toString() + "', '" + this.couleur.toString() +"', " + this.numeroHausse;
        crea.SQLHausse(req);

    }
}
