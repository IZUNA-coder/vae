package Controller;

import Modele.BD.InscriptionUtilisateur;
import Modele.Utilisateur;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControllerEnterCreerCompte implements EventHandler<KeyEvent>{
    
    private AppliVAE appli;
    private InscriptionUtilisateur inscriptionUtilisateur;
    private Utilisateur user;
    
    public ControllerEnterCreerCompte(AppliVAE appli,InscriptionUtilisateur inscriptionUtilisateur,Utilisateur user){
        this.appli = appli;
        this.inscriptionUtilisateur = inscriptionUtilisateur;
        this.user = user;
    }
    
    @Override
    public void handle(KeyEvent event) {
        // String nomUtilisateur = appli.getId();
        // String motDePasse = appli.getPassword();

        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Key Enter cliqué pour créer un compte");

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
                            if(!inscriptionUtilisateur.checkExistingUsername()){
                                if(inscriptionUtilisateur.ajouterUtilisateur()){
                                    System.out.println("Inscription réussie");
                                    this.appli.afficherPopUpErreur(false,"Inscription réussie", "Inscription réussie", "Vous pouvez maintenant vous connecter.");
                                    this.appli.afficheFenetreConnexion();
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
}