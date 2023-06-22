package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent;

public class ControllerBtnProfil implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    public ControllerBtnProfil(AppliVAE appli) {
        this.appli = appli;
    }
    @Override
    public void handle(ActionEvent event) {
        this.appli.afficheFenetreProfil();
    }
}
