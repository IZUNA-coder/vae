package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControleQuitter implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    
    public ControleQuitter(AppliVAE appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Quitter cliqué");
        //confirmation pour quitter l'appli
        if(this.appli.confirmationPopUp("Êtes-vous sûr de vouloir quitter ?","Cliquez sur OK pour quitter l'application.")){
            this.appli.quitte();
        }
    }
}
