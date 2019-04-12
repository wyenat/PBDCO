package Ruche;

public class Ruche {
    
    private static int idRuche = 0;

    public int getIdRuche() {
        return idRuche++;
    }

    public Ruche() {
        idRuche++;
    }
    
}
