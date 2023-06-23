package Controller;

import java.sql.SQLException;

import Modele.Statut;
import Modele.Vente;
import Modele.BD.GestionVentes;
import Vue.AppliVAE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;

public class ControllerBtnEditVente implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private GestionVentes gestionVentes;


    public ControllerBtnEditVente(AppliVAE appli,GestionVentes gestionVentes) {
        this.appli = appli;
        this.gestionVentes=gestionVentes;
    }
    
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Edit Vente cliqué");
        Vente venteSelected = this.appli.getLastVenteSelected();
        if(venteSelected == null){
            System.out.println("Aucune vente sélectionnée");
            this.appli.afficherPopUpErreur(true, "Erreur", "Aucune vente sélectionné", "Veuillez sélectionner une vente à supprimer.");
        } else{
            if(!this.appli.afficheFenetreEditVente(this.appli.getLastVenteSelected())){
                System.out.println("Modification annulée");
            }
            // //afficher resultats
            String id = this.appli.getVenteEditID();
            String prixBase = this.appli.getVenteEditPrixBase();
            String prixMin = this.appli.getVenteEditPrixMin();
            String nomStat = this.appli.getVenteEditStatut();
            String idObj = this.appli.getVenteEditIdObj();
            String nomObj = this.appli.getVenteEditNomObj();

            Statut statut = new Statut(nomStat);
            int idStatut = statut.getIdentifiant();
            
            if(!id.equals("")){
                System.out.println("ID : " + id);
                // Vérifier que l'ID est un nombre
                try{
                    Integer.parseInt(id);
                } catch(NumberFormatException e){
                    this.appli.afficherPopUpErreur(false, "ID invalide", "L'ID doit être un nombre", "Erreur");
                    return;
                }
                // Verifier que l'ID n'est pas déjà utilisé
                if(this.gestionVentes.checkExistingID((Integer.parseInt(id)))){
                    this.appli.afficherPopUpErreur(false, "ID invalide", "L'ID est déjà utilisé", "Erreur");
                    return;
                } else{
                    this.gestionVentes.editId(venteSelected.getIdentifiant(), Integer.parseInt(id));
                    System.out.println("ID modifié");
                }
            }
            if(!prixBase.equals("")){
                System.out.println("Prix base : " + prixBase);
                try{
                    Integer.parseInt(prixBase);
                } catch(NumberFormatException e){
                    this.appli.afficherPopUpErreur(false, "Prix invalide", "Le prix doit être un nombre", "Erreur");
                    return;
                }
                this.gestionVentes.editPrixBase(venteSelected.getIdentifiant(), Double.valueOf(prixBase));
                System.out.println("Prix base modifié");
            }
            if(!prixMin.equals("")){
                System.out.println("Prix min : " + prixMin);
                try{
                    Integer.parseInt(prixMin);
                } catch(NumberFormatException e){
                    this.appli.afficherPopUpErreur(false, "Prix invalide", "Le prix doit être un nombre", "Erreur");
                    return;
                }
                this.gestionVentes.editPrixMin(venteSelected.getIdentifiant(), Double.valueOf(prixMin));
                System.out.println("Prix min modifiée");
            }
            if(!idObj.equals("")){
                System.out.println("ID Objet : " + idObj);
                try{
                    Integer.parseInt(idObj);
                } catch(NumberFormatException e){
                    this.appli.afficherPopUpErreur(false, "ID invalide", "L'ID doit être un nombre", "Erreur");
                    return;
                }
                this.gestionVentes.editIdObj(venteSelected.getIdentifiant(),  Integer.parseInt(idObj));
                System.out.println("ID Objet modifiée");
            }
            if(!nomObj.equals("")){
                System.out.println("Nom Objet : " + nomObj);
                this.gestionVentes.editNomObj(venteSelected.getIdentifiant(), nomObj);
                System.out.println("Nom Objet modifiée");
            }
            System.out.println("Statut : " + nomStat);
            this.gestionVentes.editIdStatut(venteSelected.getIdentifiant(), idStatut);
            System.out.println("Statut modifié");
        }

        ObservableList<Vente> list = FXCollections.observableArrayList();
        this.appli.resetLastVenteSelected();

        try {
            list.clear(); // Effacer les données précédentes

            for (Vente v : this.gestionVentes.getVente()) {
                list.add(v);
            }
            this.appli.setVentesTable(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
                    
    }

}