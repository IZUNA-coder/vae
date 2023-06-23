package Controller;

import javafx.event.EventHandler;
import Modele.Vente;
import Vue.AppliVAE;
import javafx.scene.input.MouseEvent;

public class ControllerBtnObjAVendre implements EventHandler<MouseEvent> {
    private AppliVAE appli;
    private Vente vente;

    public ControllerBtnObjAVendre(AppliVAE appli,Vente vente) {
        this.appli = appli;
        this.vente = vente;
    }
    @Override
    public void handle(MouseEvent event) {
        this.appli.afficheFenetreObjAVendre(this.vente);
        
    }
}
