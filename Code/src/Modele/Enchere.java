
/**
 * Enchere
 */
public class Enchere {

    private int identifiant;
    private Double prix;
    private Utilisateur utilisateur;
    private Vente vente;
    private Objet objet;

    public int getIdentifiant() {
        return identifiant;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Vente getVente() {
        return vente;
    }

    public Objet getObjet() {
        return objet;
    }

    public Enchere(int identifiant, Double prix, Utilisateur utilisateur, Vente vente, Objet objet) {
        this.identifiant = identifiant;
        this.prix = prix;
        this.utilisateur = utilisateur;
        this.vente = vente;
        this.objet = objet;
    }

    

    

    

    
}