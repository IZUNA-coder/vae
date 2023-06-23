package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Modele.Vente;
import Vue.AppliVAE;

public class ControllerBtnObjtEncherir implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    private Vente vente;

    public ControllerBtnObjtEncherir(AppliVAE appli,Vente vente) {
        this.appli = appli;
        this.vente = vente;
    }
    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreEncherir(this.vente);
        
    }
}
