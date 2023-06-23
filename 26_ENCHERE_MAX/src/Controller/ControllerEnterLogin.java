package Controller;

import Modele.BD.ConnexionUtilisateur;
import Modele.Utilisateur;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControllerEnterLogin implements EventHandler<KeyEvent>{
    
    private AppliVAE appli;
    private ConnexionUtilisateur connexionUtilisateur;
    private Utilisateur user;
    
    public ControllerEnterLogin(AppliVAE appli,ConnexionUtilisateur connexionUtilisateur,Utilisateur user){
        this.appli = appli;
        this.connexionUtilisateur = connexionUtilisateur;
        this.user=user;
    }
    
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Key Enter cliqué pour se connecter");
            
            // teste si login fonctionnel , si oui afficher Accueil
            String username = appli.getUsername();
            String password = appli.getPassword();
            this.appli.setUser(connexionUtilisateur.getUser(username));

            System.out.println(password);

            user.setUsername(username);
            user.setPassword(password);
            if(!connexionUtilisateur.checkDonnéeVide()){
                if(connexionUtilisateur.connexionUtilisateur()){
                    System.out.println("Connexion réussie (user)");
                    this.appli.afficherChargement();
                    this.appli.chargerDonnees();
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
}