package Ruche;

public enum Contenu {
    VIDE,
    LARVES,
    POLLEN,
    MIEL
  ;  

    public static Contenu[] getAll(){
        return new Contenu[]{
            VIDE,
            LARVES,
             POLLEN,
             MIEL
        };
    }
}
