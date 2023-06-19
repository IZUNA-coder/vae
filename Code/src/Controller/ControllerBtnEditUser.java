package Controller;

import Modele.Role;
import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;
public class ControllerBtnEditUser implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private GestionUtilisateurs gestionUsers;


    public ControllerBtnEditUser(AppliVAE appli,GestionUtilisateurs gestionUsers) {
        this.appli = appli;
        this.gestionUsers=gestionUsers;
    }
    
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Edit User cliqué");
        Utilisateur userSelected = this.appli.getLastUserSelected();
        if(userSelected == null){
            System.out.println("Aucun utilisateur sélectionné");
            this.appli.afficherPopUpErreur(false, "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur", "Erreur");
        } else{
            if(!this.appli.afficheFenetreEditUser(this.appli.getLastUserSelected())){
                System.out.println("Modification annulée");
            }
            // //afficher resultats
            String id = this.appli.getEditID();
            String username = this.appli.getEditUsername();
            String email = this.appli.getEditEmail();
            String role = this.appli.getEditRole();
            Role newRole = new Role(role);
            if(!id.equals("")){
                System.out.println("ID : " + id);
                // Vérifier que l'ID est un nombre
                try{
                    Integer.parseInt(id);
                } catch(NumberFormatException e){
                    this.appli.afficherPopUpErreur(false, "ID invalide", "L'ID doit être un nombre", "Erreur");
                    return;
                }
                // Verifier que l'ID n'est pas déjà utilisé
                if(this.gestionUsers.checkExistingID((Integer.parseInt(id)))){
                    this.appli.afficherPopUpErreur(false, "ID invalide", "L'ID est déjà utilisé", "Erreur");
                    return;
                } else{
                    this.gestionUsers.editId(userSelected.getId(), id);
                    System.out.println("ID modifié");
                }
            }
            if(!username.equals("")){
                System.out.println("Username : " + username);
                this.gestionUsers.editUsername(userSelected.getId(), username);
                System.out.println("Username modifié");
            }
            if(!email.equals("")){
                System.out.println("Email : " + email);
                this.gestionUsers.editEmail(userSelected.getId(), email);
                System.out.println("Email modifiée");
            }
            System.out.println("Role : " + role);
            this.gestionUsers.editRole(userSelected.getId(), newRole);
            System.out.println("Role modifié");
        }

    }

}