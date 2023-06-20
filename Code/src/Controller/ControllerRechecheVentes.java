package Controller;

import Vue.AppliVAE;
import Modele.Vente;
import Modele.BD.GestionVentes;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerRechecheVentes implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private GestionVentes gestionVentes;
    private ObservableList<Vente> ventes;
    
    public ControllerRechecheVentes(AppliVAE appli,GestionVentes gestionVentes,ObservableList<Vente> ventes){
        this.appli = appli;
        this.gestionVentes=gestionVentes;
        this.ventes = FXCollections.observableArrayList();
    }
    
    @Override
    public void handle(ActionEvent event) {
         System.out.println("Enter Rechercher Ventes cliqué");
            try {
                this.ventes.clear(); // Effacer les données précédentes

                for (Vente v : this.gestionVentes.getVente()) {
                    this.ventes.add(v);
                }
                this.appli.setVentesTable(this.ventes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}