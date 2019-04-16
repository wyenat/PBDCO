package Ruche;



public class Cadre extends Materiel {
    
    private Etat etat;
    private Contenu contenu;    

    public Cadre(Contenu cont, Etat etat, Materiau mat) {
        super(mat);
        this.contenu = contenu;
        this.etat = etat;
    }

    public Etat getEtat() {
        return etat;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public void creer() {
        // TODO
    }
    
    
    
}
