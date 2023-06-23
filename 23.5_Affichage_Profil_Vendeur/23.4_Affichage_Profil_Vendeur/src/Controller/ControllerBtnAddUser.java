package Controller;

import Modele.Role;
import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControllerBtnAddUser implements EventHandler<ActionEvent>{

    private AppliVAE appli;
    private GestionUtilisateurs gestionUsers;


    public ControllerBtnAddUser(AppliVAE appli,GestionUtilisateurs gestionUsers) {
        this.appli = appli;
        this.gestionUsers=gestionUsers;
    }
    
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Add User cliqué");
            if(!this.appli.afficheFenetreAddUser()){
                System.out.println("Modification annulée");
            } else{
                //afficher resultats
                String username = this.appli.getAddUsername();
                String email = this.appli.getAddEmail();
                String password = this.appli.getAddPassword();
                Role role = this.appli.getAddRole();


                Utilisateur user = new Utilisateur();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);
                this.gestionUsers.ajouterUtilisateur(user);
                this.appli.afficherPopUpErreur(false,"Utilisateur ajouté", "Utilisateur ajouté", "Appuyer sur actualiser pour rafraîchir les données.");
            }
    }

}
