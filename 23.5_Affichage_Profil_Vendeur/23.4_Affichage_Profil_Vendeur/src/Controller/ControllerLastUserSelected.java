package Controller;

import Modele.Utilisateur;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ControllerLastUserSelected implements EventHandler<MouseEvent> {
    private AppliVAE appli;
    private Utilisateur user;
    private TableView<Utilisateur> table;

    public ControllerLastUserSelected(AppliVAE appli, TableView<Utilisateur> table) {
        this.appli=appli;
        this.table = table;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Nouvel utilisateur sélectionné");
        this.user=table.getSelectionModel().getSelectedItem();
        if (this.user != null) {
            this.appli.updateLastSelectedUser(this.user);
        }
    }
}
