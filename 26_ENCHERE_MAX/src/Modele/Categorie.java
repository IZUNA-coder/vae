package Modele;

import java.util.Arrays;
import java.util.List;

public class Categorie {
    public final static List<String> nomCategorie = Arrays.asList("Vêtement","Ustensile Cuisine","Meuble","Outil");

    private int identifiant;
    private String nom;
    /**
     * Constructeur de la classe Categorie
     * @param identifiant
     * @param nom
     */
    public Categorie(int identifiant, String nom) {
        this.identifiant = identifiant;
        this.nom = nom;
    }
    /**
     * Constructeur de la classe Categorie
     * @param identifiant
     */
    public Categorie(int identifiant) {
        this.identifiant = identifiant;
        this.nom = nomCategorie.get(identifiant-1);
    }
    /**
     * Constructeur de la classe Categorie
     * @param nom
     */
    public Categorie(String nom) {
        this.nom = nom;
        this.identifiant = nomCategorie.indexOf(nom)+1;
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
}
