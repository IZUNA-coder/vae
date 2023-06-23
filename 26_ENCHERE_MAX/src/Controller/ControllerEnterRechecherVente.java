package Controller;

import Vue.AppliVAE;
import Modele.Vente;
import Modele.BD.GestionVentes;

import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerEnterRechecherVente implements EventHandler<KeyEvent>{
    
    private AppliVAE appli;
    private GestionVentes gestionVentes;
    private ObservableList<Vente> ventes;
    
    public ControllerEnterRechecherVente(AppliVAE appli,GestionVentes gestionVentes,ObservableList<Vente> ventes){
        this.appli = appli;
        this.gestionVentes=gestionVentes;
        this.ventes = FXCollections.observableArrayList();
    }
    
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Enter Rechercher Ventes cliqué");
            // ...
            try {
                this.ventes.clear(); // Effacer les données précédentes

                for (Vente v : this.gestionVentes.getVenteRecherche(this.appli.getSearchVente())) {
                    this.ventes.add(v);
                }
                this.appli.setVentesTable(this.ventes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}