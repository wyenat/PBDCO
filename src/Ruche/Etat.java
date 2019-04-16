package Ruche;

public enum Etat {
    NEUF,
    BON,
    MOYEN,
    MAUVAIS,
    CASSE
  ;  

    public static Etat[] getAll(){
        return new Etat[]{
            NEUF,
    BON,
    MOYEN,
    MAUVAIS,
    CASSE
        };
    }
}
