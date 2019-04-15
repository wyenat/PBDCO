package Ruche;

import SQL.Affichage;

public class Ruche {
    private int idRuche;

    public int getIdRuche() {
        System.out.println("On get : " + idRuche);
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
                    System.out.println("Courant = " +  potentiel+ ", max = " + candidat);
                }
            }
        }
        this.idRuche = candidat + 1;
               
    }
    
}
