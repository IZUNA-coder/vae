package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent;

public class ControllerBtnVendre implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    public ControllerBtnVendre(AppliVAE appli) {
        this.appli = appli;
    }
    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreVendre();
        
    }
}
