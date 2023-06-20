package Modele;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;

public class Vente {
    
    private int identifiant;
    private double prixBase;
    private double prixMin;
    private Date debutVente;
    private Date finVente;
    private Statut statut;
    private Objet objet;
    private List<Enchere> lesEncheres;

    
    public List<Enchere> getLesEncheres() {
        return lesEncheres;
    }

    public void setLesEncheres(List<Enchere> lesEncheres) {
        this.lesEncheres = lesEncheres;
    }

    public Vente(int identifiant, double prixBase, double prixMin, Date debutVente, Date finVente,Statut statut,Objet objet) {
        this.identifiant = identifiant;
        this.prixBase = prixBase;
        this.prixMin = prixMin;
        this.debutVente = debutVente;
        this.finVente = finVente;
        this.statut = statut;
        this.objet = objet;
    }

    public Vente(int identifiant, double prixBase, double prixMin, Date debutVente, Date finVente,int idSt,Objet objet) {
        this.identifiant = identifiant;
        this.prixBase = prixBase;
        this.prixMin = prixMin;
        this.debutVente = debutVente;
        this.finVente = finVente;
        this.statut = new Statut(idSt);
        this.objet = objet;
    }
    
    public Vente(Class<Integer> class1) {
    }

    public int getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    public double getPrixBase() {
        return prixBase;
    }
    public void setPrixBase(double prixBase) {
        this.prixBase = prixBase;
    }
    public double getPrixMin() {
        return prixMin;
    }
    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }
    public Date getDebutVente() {
        return debutVente;
    }
    public void setDebutVente(Date debutVente) {
        this.debutVente = debutVente;
    }
    public Date getFinVente() {
        return finVente;
    }
    public void setFinVente(Date finVente) {
        this.finVente = finVente;
    }
    public Statut getStatut() {
        return statut;
    }
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getNomStatut() {
        return statut.getNom();
    }


    public Objet getObjet() {
        return objet;
    }
    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public int getIdObjet() {
        return objet.getIdentifiant();
    }


    public Enchere getEnchereMax(){
        List<Enchere> copie= new ArrayList<>(this.lesEncheres);
        return Collections.max(copie, new ComparateurEnchere());

    }

    public Utilisateur getAcheteurActuel(){
        return this.getEnchereMax().getUtilisateur();
    }

    public void ajouteEnchere(Utilisateur util, double prix, Date dateHeure) throws EnchereTropFaibleException, MemeUtilisateurException{
        if (util.equals(this.getAcheteurActuel())){
            throw new MemeUtilisateurException();
        }
        if (prix<this.getEnchereMax().getPrix()){
            throw new EnchereTropFaibleException();
        }
        this.lesEncheres.add(new Enchere(dateHeure, prix, util, this, this.objet));

    }
    
}
