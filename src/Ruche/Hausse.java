package Ruche;

public class Hausse {
    
    private int numeroHausse;
    private char couleur;

    public Hausse(int idMateriel, char materiau) {
        this.numeroHausse = idMateriel;
        this.couleur = materiau;
    }
    
    

    public int getnumeroHausse() {
        return numeroHausse;
    }

    public char getCouleur() {
        return couleur;
    }
    
    
    
}
