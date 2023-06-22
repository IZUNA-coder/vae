package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerRechercheVenteUser implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private TextField recherche;
    
    public ControllerRechercheVenteUser(AppliVAE appli, TextField recherche){
        this.appli = appli;
        this.recherche = recherche;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Rechercher Vente (user) cliqué");
        System.out.println("Recherche : " + this.recherche.getText());
        this.appli.setRecherche(this.recherche.getText());
        this.appli.afficheFenetreRechercheEnchere(this.recherche.getText());
    }
}

