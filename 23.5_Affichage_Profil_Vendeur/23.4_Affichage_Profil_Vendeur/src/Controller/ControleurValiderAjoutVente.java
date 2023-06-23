package Controller;

import Vue.FenetreVente;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControleurValiderAjoutVente implements EventHandler<ActionEvent> {


    private FenetreVente fenetreVente;
    private Alert alert;

    public ControleurValiderAjoutVente(FenetreVente fenetreVente) { 
    
        this.fenetreVente = fenetreVente;
        this.alert = this.fenetreVente.alertErreurChamps();
    }

    @Override
    public void handle(ActionEvent arg0) {
        

        boolean erreur = false;

        String nom = this.fenetreVente.getNom();

        if (nom.equals("")) {
            this.fenetreVente.rougeNom();
            erreur = true;
        }
        else{
            erreur = false;
            this.fenetreVente.vertNom();
        }

        Double prixDeBase = this.fenetreVente.getPrixDeBase();

        
        if (prixDeBase.equals(0.0)){
            this.fenetreVente.rougePrixDeBase();
            erreur = true;
        }
        else {
            erreur = false;
            this.fenetreVente.vertPrixDeBase();
        }

        Double prixMinimum = this.fenetreVente.getPrixMinimum();

        if (prixMinimum.equals(0.0)){
            this.fenetreVente.rougePrixMinimum();
            erreur = true;
        }
        else {
            erreur = false;
            this.fenetreVente.vertPrixMinimum();
        }


        if (prixDeBase > prixMinimum) {
            this.fenetreVente.rougePrixDeBase();
            this.fenetreVente.rougePrixMinimum();
            this.alert = this.fenetreVente.alertErreurPrix();
            this.alert.showAndWait();
            erreur = true;
        }
        else{
            erreur = false;
            this.alert = this.fenetreVente.alertErreurChamps();
        }


        if (this.fenetreVente.getDateDebut() == null) {
            this.fenetreVente.rougeDateDebut();
            erreur = true;
        }
        else {
            erreur = false;
            this.fenetreVente.vertDateDebut();
        }

        if (this.fenetreVente.getDateFin() == null) {
            this.fenetreVente.rougeDateFin();
            erreur = true;
        }
        else {
            erreur = false;
            this.fenetreVente.vertDateFin();
        }

        if (this.fenetreVente.getDateFin() != null &&
            this.fenetreVente.getDateDebut() != null &&
            this.fenetreVente.getDateDebut().isAfter(this.fenetreVente.getDateFin())) {
            erreur = true;
            this.fenetreVente.rougeDateDebut();
            this.fenetreVente.rougeDateFin();
            this.alert = this.fenetreVente.alertErreurDate();
            this.alert.showAndWait();
        }

        else{
            erreur = false;
            this.alert = this.fenetreVente.alertErreurChamps();
        }

        
        String description = this.fenetreVente.getDescription();

        if (description.equals("")) {
            erreur = true;
            this.fenetreVente.rougeDescription();
        }
        else {
            erreur = false;
            this.fenetreVente.vertDescription();
        }

        int categorie = this.fenetreVente.getCategorie();

        if (categorie == 0) {
            this.fenetreVente.rougeCategorie();
            erreur = true;
        }
        else {
            erreur = false;
            this.fenetreVente.vertCategorie();
        }



        if (erreur){
            this.alert.showAndWait();
        }

        if (!this.fenetreVente.getNom() .equals("")){
            System.out.println("Image : " + this.fenetreVente.getLienImage());
        }
        


    }


                

}
    

