package Controller;

import Vue.AppliVAE;

import java.sql.SQLException;

import Modele.Vente;
import Modele.BD.GestionVentes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;
public class ControllerBtnDeleteVente implements EventHandler<ActionEvent>{
    
    private GestionVentes gestionVentes;
    private AppliVAE appli;


    public ControllerBtnDeleteVente(AppliVAE appli, GestionVentes gestionVentes) {
        this.appli = appli;
        this.gestionVentes = gestionVentes;
    }
    
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Delete Vente cliqué");
        Vente dernierVenteSelected = this.appli.getLastVenteSelected();
        // Vérifiez d'abord si une ligne est sélectionnée
        if (dernierVenteSelected != null) {
            // Obtenez l'ID de la vente sélectionné
            int id = dernierVenteSelected.getIdentifiant();
            if(this.appli.afficherPopUpSupprimerVente(dernierVenteSelected)){
                try {
                    this.gestionVentes.supprimerVente(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.appli.afficherPopUpErreur(false,"Vente supprimé", "Vente supprimé", "Appuyer sur actualiser pour rafraîchir les données.");
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
        } else {
            // Aucune ligne sélectionnée, affichez un message d'erreur
            this.appli.afficherPopUpErreur(true, "Erreur", "Aucune vente sélectionné", "Veuillez sélectionner une vente à supprimer.");
        }
    }

}