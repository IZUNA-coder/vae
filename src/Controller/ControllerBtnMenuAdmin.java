package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;

public class ControllerBtnMenuAdmin implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private GestionUtilisateurs gestionUsers;
    
    public ControllerBtnMenuAdmin(AppliVAE appli,GestionUtilisateurs gestionUsers){
        this.appli = appli;
        this.gestionUsers=gestionUsers;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        if (button.getText().contains("Utilisateurs")){
            System.out.println("Bouton Gestion Users cliqué");
            this.appli.afficheFenetreGestionUsers();
        }
        else if(button.getText().contains("Signalements")){
            System.out.println("Bouton Gestion Signalements cliqué");
            this.appli.afficheFenetreGestionSignalements();
        }
        if (button.getText().contains("Ventes")){
            System.out.println("Bouton Gestion Ventes cliqué");
            String nbTotVentes= String.valueOf(this.gestionUsers.getNbTotVentes());
            String nbVentesValidee= String.valueOf(this.gestionUsers.getNbVentesValidee());
            String nbVentesNonConclus= String.valueOf(this.gestionUsers.getNbVentesNonConclus());
            this.appli.setNbVentesLabel(nbTotVentes, nbVentesValidee, nbVentesNonConclus);
            this.appli.afficheFenetreGestionVentes();
        }
        else if(button.getText().contains("Contrats")){
            System.out.println("Bouton Gestion Contrats cliqué");
            this.appli.afficheFenetreGestionContrats();
        }
        if (button.getText().contains("Entreprise")){
            System.out.println("Bouton Gestion Entreprise cliqué");
            String nbTotUsers= String.valueOf(this.gestionUsers.getNbTotUsers());
            String nbUsersActif= String.valueOf(this.gestionUsers.getNbUsersActif());
            String nbUsersInactif= String.valueOf(this.gestionUsers.getNbUsersInactif());
            this.appli.setNbUserLabel(nbTotUsers, nbUsersActif, nbUsersInactif);
            this.appli.afficheFenetreGestionEntreprise();
        }
        else if(button.getText().contains("Paramètres")){
            System.out.println("Bouton Gestion Parametres cliqué");
            this.appli.afficheFenetreGestionParametres();
        }
    }
}
