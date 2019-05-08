package Ruche;

import Controleurs.ControleurCapteurs;
import Controleurs.ControleurInterface;
import InterfaceGraphique.Erreur;
import SQL.Affichage;
import SQL.Création;
import SQL.Destruction;
import SQL.Modification;
import javax.swing.JList;

public class Ruche {

    public static void dissocier(String currentRucheId) {
        Destruction destruction = new Destruction();
        ControleurCapteurs contC = new ControleurCapteurs();
        Affichage aff = new Affichage();
        for (String hausse : aff.SQLCompositionRuche("idMateriel", "idRuche = " + currentRucheId).split(",")){
            // On enlève les capteurs des cadres, et les cadres des hausses.
            System.out.println(hausse.split(" ")[0]);
            if (hausse.split(" ")[0].length() != 0){
                for (String cadre : aff.SQLCompositionHausse("idMaterielCadre", "idMaterielHausse = " + hausse.split(" ")[0]).split(",")){
                contC.dissocierCapteurPoids(cadre);
                 System.out.println(cadre.split(" ")[0]);
            }
            Hausse.dissocier(hausse.split(" ")[0]);
            }    
            contC.dissocierCapteurTemperature(hausse.split(" ")[0]);
            
        }
        destruction.SQLCompositionRuche("idRuche = " +currentRucheId);
        destruction.SQLRuche("idRuche = " +currentRucheId);
        
    }
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

    public void creer(String race, int age,String couleur, String nomRuche, JList<String> listC, JList<String> listP, JList<String> listT, JList<String> listH) {
        Création crea = new Création();
        Modification modif = new Modification();
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
        crea.SQLRuche(req);
//        
//        //Association
        String asso = listC.getSelectedValue().split(" ")[0];
        req = asso + ", " + idRuche;
        crea.SQLCompositionRuche(req);
        
        asso = listP.getSelectedValue().split(" ")[0];
        req = asso + ", " + idRuche;
        crea.SQLCompositionRuche(req);
        
        asso = listT.getSelectedValue().split(" ")[0];
        req = asso + ", " + idRuche;
        crea.SQLCompositionRuche(req);
        
        for (String s :  listH.getSelectedValuesList()){
            asso = s.split(" ")[0];
            req = asso;
            modif.SQLCompositionRuche("" + idRuche, req);
        }
        
    }
    
}
