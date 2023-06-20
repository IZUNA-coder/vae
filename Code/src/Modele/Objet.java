package Modele;

public class Objet {
    
    private int identifiant;
    private String nom;
    private String description;
    private Categorie categorie;
    private Utilisateur utilisateur;

    
    public Objet(int identifiant, String nom, String description, int idUt,int idCat) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.description = description;
        this.utilisateur = new Utilisateur();
        this.utilisateur.setId(idUt);
        this.categorie = new Categorie(idCat);
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
