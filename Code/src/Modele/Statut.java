package Modele;

public class Statut {
    private char identifiant;
    private String nom;
    
    public Statut(char identifiant, String nom) {
        this.identifiant = identifiant;
        this.nom = nom;
    }

    public char getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(char identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
