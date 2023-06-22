package Controller;

import javafx.event.EventHandler;
import Modele.BD.ConnexionUtilisateur;
import Modele.Utilisateur;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerBtnConnexion implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private ConnexionUtilisateur connexionUtilisateur;
    private Utilisateur user;
    
    public ControllerBtnConnexion(AppliVAE appli,ConnexionUtilisateur connexionUtilisateur,Utilisateur user){
        this.appli = appli;
        this.connexionUtilisateur = connexionUtilisateur;
        this.user=user;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Connexion cliqué");

        // teste si login fonctionnel , si oui afficher Accueil
        String username = appli.getUsername();
        String password = appli.getPassword();

        this.user.setUsername(username);
        this.user.setPassword(password);

        if(!connexionUtilisateur.checkDonnéeVide()){
            if(connexionUtilisateur.connexionUtilisateur()){
                System.out.println("Connexion réussie (user)");
                this.appli.setUser(connexionUtilisateur.getUser(username));
                this.appli.afficherChargement();
                this.appli.afficheFenetreAccueil();
            } else{
                if(connexionUtilisateur.connexionAdmin()){
                    System.out.println("Connexion réussie (admin)");
                    this.appli.afficheFenetreAdmin();
                } else{
                System.out.println("Erreur lors de la connexion");
                this.appli.afficherPopUpErreur(true,"Erreur de connexion", "Mot de passe ou Nom d'utilisateur incorrect", "Veuillez réessayer.");
                }
            }
        } else{
            System.out.println("Erreur lors de la connexion");
            this.appli.afficherPopUpErreur(true,"Erreur de connexion", "Champs incomplets", "Veuillez réessayer.");
        }
    }
}
