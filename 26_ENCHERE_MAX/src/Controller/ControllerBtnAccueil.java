package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerBtnAccueil implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    
    public ControllerBtnAccueil(AppliVAE appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Accueil cliqué");
        //confirmation pour quitter l'appli
        this.appli.afficheFenetreAccueil();
    }
}
