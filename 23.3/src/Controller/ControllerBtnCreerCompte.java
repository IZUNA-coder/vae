package Controller;

import javafx.event.EventHandler;
import Modele.BD.GestionUtilisateurs;
import Modele.BD.InscriptionUtilisateur;
import Modele.Utilisateur;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerBtnCreerCompte implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private InscriptionUtilisateur inscriptionUtilisateur;
    private Utilisateur user;
    private GestionUtilisateurs gestionUsers;
    
    public ControllerBtnCreerCompte(AppliVAE appli,InscriptionUtilisateur inscriptionUtilisateur,Utilisateur user,GestionUtilisateurs gestionUsers){
        this.appli = appli;
        this.inscriptionUtilisateur = inscriptionUtilisateur;
        this.user = user;
        this.gestionUsers = gestionUsers;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Créer un compte cliqué");

        String username = appli.getUsername2();
        String password = appli.getPassword2();
        String email = appli.getEmail();
        String confirmPassword = this.appli.getConfirmPassword();

        System.out.println(username);
        System.out.println(email);
        System.out.println(password);
        System.out.println(confirmPassword);

        user.setUsername(username);
        user.setEmail(email);
        if(password.equals(confirmPassword)){
            user.setPassword(confirmPassword);
            if(!inscriptionUtilisateur.checkDonnéeVide()){
                if(inscriptionUtilisateur.checkEmail()){
                    if(inscriptionUtilisateur.checkPassword()){
                        if(!gestionUsers.checkExistingUsername(username)){
                            if(!gestionUsers.checkExistingEmail(email)){
                                if(gestionUsers.ajouterUtilisateur(user)){
                                    System.out.println("Inscription réussie");
                                    this.appli.afficherPopUpErreur(false,"Inscription réussie", "Inscription réussie", "Vous pouvez maintenant vous connecter.");
                                    this.appli.afficheFenetreConnexion();
                                }
                            } else{
                                System.out.println("Erreur lors de l'inscription");
                                this.appli.afficherPopUpErreur(true,"Erreur d'inscription", "Email déjà utilisé", "Veuillez réessayer.");
                            }
                        } else{
                            System.out.println("Erreur lors de l'inscription");
                            this.appli.afficherPopUpErreur(true,"Erreur d'inscription", "Nom d'utilisateur déjà utilisé", "Veuillez réessayer.");
                        }
                    } else{
                        System.out.println("Erreur lors de l'inscription");
                        this.appli.afficherPopUpErreur(true,"Erreur d'inscription", "Le mot de passe doit contenir au minimum : 8 caractères, 1 chiffre", "Veuillez réessayer.");
                    }
                } else{
                    System.out.println("Erreur lors de l'inscription");
                        this.appli.afficherPopUpErreur(true,"Erreur d'inscription", "L'email doit contenir qu'un @ et . et au moins 1 lettre au début", "Veuillez réessayer.");
                }
            } else{
                System.out.println("Erreur lors de l'inscription");
                this.appli.afficherPopUpErreur(true,"Erreur d'inscription", "Champs incomplets", "Veuillez réessayer.");
            }
        } else{
            System.out.println("Erreur lors de l'inscription");
            this.appli.afficherPopUpErreur(true,"Erreur d'inscription", "Les mots de passe ne correspondent pas", "Veuillez réessayer.");
        }
    }
}
