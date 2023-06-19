public class Objet {
    
    private int identifiant;
    private String nom;
    private String description;
    private Categorie categorie;
    private Utilisateur utilisateur;

    
    public Objet(int identifiant, String nom, String description, Categorie categorie, Utilisateur utilisateur) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
    }
    
    public int getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


}
