package Modele;

import java.sql.Date;

/**
 * Enchere
 */
public class Enchere {

    private Date dateHeure;
    private double prix;
    private Utilisateur utilisateur;
    private Vente vente;
    private Objet objet;

    public Date getDateHeure() {
        return dateHeure;
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

    public Enchere(Date dateHeure, double prix, Utilisateur utilisateur, Vente vente, Objet objet) {
        this.dateHeure = dateHeure;
        this.prix = prix;
        this.utilisateur = utilisateur;
        this.vente = vente;
        this.objet = objet;
    }

    

    

    

    
}