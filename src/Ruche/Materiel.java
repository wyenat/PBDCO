package Ruche;

public class Materiel {
    
    private int idMateriel;
    private char materiau;

    public Materiel(int idMateriel, char materiau) {
        this.idMateriel = idMateriel;
        this.materiau = materiau;
    }
    
    

    public int getIdMateriel() {
        return idMateriel;
    }

    public char getMateriau() {
        return materiau;
    }
    
    
    
}
