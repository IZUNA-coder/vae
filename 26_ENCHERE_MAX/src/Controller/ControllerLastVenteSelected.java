package Controller;

import Modele.Vente;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ControllerLastVenteSelected implements EventHandler<MouseEvent> {
    private AppliVAE appli;
    private Vente vente;
    private TableView<Vente> table;

    public ControllerLastVenteSelected(AppliVAE appli, TableView<Vente> table) {
        this.appli=appli;
        this.table = table;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Nouvel vente sélectionné");
        this.vente=table.getSelectionModel().getSelectedItem();
        if (this.vente != null) {
            this.appli.updateLastSelectedVente(this.vente);
        }
    }
}
