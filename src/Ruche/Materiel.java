package Ruche;

import SQL.Affichage;
import SQL.Création;

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
        System.out.println("Création de " + this.getClass().toString());
        Création crea = new Création();
        crea.SQLMateriau("" + idMateriel +"," + "'" +this.materiau.toString()+"'", this.getClass());
    }
}
