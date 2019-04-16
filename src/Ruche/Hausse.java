package Ruche;

public class Hausse extends Materiel {
    
    private int numeroHausse;
    private Couleur couleur;

    public Hausse(int numero, Materiau materiau, Couleur couleur) {
        super(materiau);
        this.numeroHausse = numero;
        this.couleur = couleur;
    }
    
    

    public int getnumeroHausse() {
        return numeroHausse;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    
    
    
}
