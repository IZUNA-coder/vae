package Modele;

public class Statut {
    private int idSt;
    private String nom;
    private final static String[] NOMSTATUT_STRINGS = {"A venir","En cours","A valider","Validé","Non conclue"};
    
    public Statut(int idSt, String nom) {
        this.idSt = idSt;
        this.nom = nom;
    }

    public Statut(int idSt) {
        this.idSt = idSt;
        this.nom = NOMSTATUT_STRINGS[idSt-1];
    }

    public Statut(String nom) {
        this.nom = nom;
        this.idSt = getIdStatut(nom);
    }

    public int getIdentifiant() {
        return idSt;
    }

    public int getIdStatut(String nomStatut){
        int id=0;
        for(int i=0;i<NOMSTATUT_STRINGS.length;i++){
            if(nomStatut.equals(NOMSTATUT_STRINGS[i])){
                id=i+1;
            }
        }
        return id;
    }

    public void setIdentifiant(int identifiant) {
        this.idSt = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
