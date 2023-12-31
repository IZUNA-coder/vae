package Modele;

public class Statut {
    private int idSt;
    private String nom;
    public final static String[] NOMSTATUT_STRINGS = {"A venir","En cours","A valider","Validée","Non conclue"};
    
    public Statut(int idSt, String nom) {
        this.idSt = idSt;
        this.nom = nom;
    }
    /**
     * Constructeur de la classe Statut
     * @param idSt
     */
    public Statut(int idSt) {
        this.idSt = idSt;
        this.nom = NOMSTATUT_STRINGS[idSt-1];
    }
    /**
     * Constructeur de la classe Statut
     * @param nom
     */
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
