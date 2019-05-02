package Ruche;

import InterfaceGraphique.Erreur;
import SQL.Affichage;
import SQL.Création;
import javax.swing.JList;

public class Ruche {
    private int idRuche;

    public int getIdRuche() {
        return idRuche;
    }

    public Ruche() {
        String req = "idRuche";
        Affichage aff = new Affichage();
        String resultat = aff.SQLRuche(req);
        int candidat = -1;
        if (resultat!=""){
            for (String potentiel : resultat.split(" ")){
                if (Integer.parseInt(potentiel) > candidat){
                    candidat = Integer.parseInt(potentiel);
                }
            }
        }
        this.idRuche = candidat + 1;
               
    }

    public boolean verifier(String race, int age, String nomRuche) {
        boolean ret = true;
        if (age < 0){
            Erreur.main("Un âge doit être positif ! Vous avez rentré"
                    + age + " !");
            ret = false;
        }
        System.out.println(nomRuche + nomRuche.equals(""));
        if (nomRuche.equals("")){
                Erreur.main("Un nom ne peut pas être vide !");
                ret = false;
        }
        if (race.equals("")){
                Erreur.main("Une race ne peut pas être vide !");
                ret = false;
        }
        return ret;
    }

    public void creer(String race, int age, String nomRuche, String couleur, JList<String> listC, JList<String> listP, JList<String> listT) {
        Création crea = new Création();
        // Création de la ruche en elle-même
        String req = "";
        req += "" + idRuche;
        // raceReine :
        req += ", " + "'"+ race +"'";
        // ageReine :
        req += ", " + age;
        // couleurReine :
        req += ", " + "'" + couleur + "'";
        // nomRuche :
        req += ", " + "'" + nomRuche + "'";
//        crea.SQLRuche(req);
//        
//        //Association
//        String asso = listC.getSelectedValue().split(" ")[0];
//        req = asso + ", " + idRuche;
//        crea.SQLCompositionRuche(req);
//        
//        asso = listP.getSelectedValue().split(" ")[0];
//        req = asso + ", " + idRuche;
//        crea.SQLCompositionRuche(req);
//        
//        asso = listT.getSelectedValue().split(" ")[0];
//        req = asso + ", " + idRuche;
//        crea.SQLCompositionRuche(req);
    }
    
}
