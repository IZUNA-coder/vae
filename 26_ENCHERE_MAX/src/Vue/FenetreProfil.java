package Vue;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class FenetreProfil extends BorderPane {
    private BorderPane coteGauche;
    private BorderPane coteDroit;
    private VBox lesInfos;
    private Button btnVAE;
    private TextField tfPseudo;
    private TextField tfMail;
    private TextField tfMDP;
    private Button btnModifierPseudo;
    private Button btnModifierMDP;
    private Button btnModifierMail;
    private Button btnSupprimerCompte;
    private Button btnDeconnexion;
    /**
     * Constructeur de la fenetre profil
     * @param btnVAE
     * @param tfPseudo
     * @param tfMDP
     * @param tfMail
     * @param btnModifierPseudo
     * @param btnModifierMDP
     * @param btnModifierMail
     * @param btnSupprimerCompte
     * @param btnDeconnexion
     */
    public FenetreProfil(Button btnVAE, TextField tfPseudo, TextField tfMDP, TextField tfMail, Button btnModifierPseudo, Button btnModifierMDP, Button btnModifierMail, Button btnSupprimerCompte, Button btnDeconnexion){
        super();
        this.btnVAE = btnVAE;
        this.btnModifierPseudo = btnModifierPseudo;
        this.btnModifierMDP = btnModifierMDP;
        this.btnModifierMail = btnModifierMail;
        this.btnSupprimerCompte = btnSupprimerCompte;
        this.btnDeconnexion = btnDeconnexion;
        this.tfPseudo = tfPseudo;
        this.tfMDP = tfMDP;
        this.tfMail = tfMail;
        this.init();
        this.laPage();
        //ne pas mettre le cursor dans un textfield au lancement de la fenetre
        this.tfPseudo.setFocusTraversable(false);
    }
    /**
     * Permet d'initialiser la page
     */
    public void init() {
        this.coteGauche = new BorderPane();
        this.coteDroit = new BorderPane();
    }
    /**
     * Permet de mettre en place la page
     * @return HBox
     */
    public HBox hautCoteGauche() {
        HBox top = new HBox();
        HBox contientTitre = new HBox();
        Label titre = new Label("Modifications du profil");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 50));
        contientTitre.getChildren().add(titre);
        contientTitre.setAlignment(Pos.CENTER_RIGHT);
        top.getChildren().addAll(this.btnVAE, contientTitre);
        top.setSpacing(430);
        return top;
    }
    /**
     * Permet de d'ajouter le pseudo
     * @return Label
     */
    public Label pseudo() {
        Label pseudo = new Label("Nom d'utilisateur");
        pseudo.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 20));
        return pseudo;
    }
    /**
     * Permt de retourner le textfield du pseudo
     * @return TextField
     */
    public TextField tfPseudo() {
        return this.tfPseudo;
    }
    /**
     * Permet de retourner le bouton de modification du pseudo
     * @return Button
     */
    public Button modifier() {
        Button modifier = new Button("MODIFIER");
        modifier.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 20));
        modifier.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 25px;");
        return modifier;
    }
    /**
     * Permet de retourner le lable pour créer l'espace du mot de passe 
     * @return Button
     */
    public Label mdp() {
        Label mdp = new Label("Mot de passe");
        mdp.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 20));
        return mdp;
    }
    /**
     * Permet de retourner le textfield du mot de passe
     * @return TextField
     */
    public TextField tfMDP() {
        return this.tfMDP;
    }
    /**
     * Permet de retourner le label pour le mail
     * @return
     */
    public Label Mail() {
        Label mail = new Label("Mail");
        mail.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 20));
        return mail;
    }
    /**
     * Permet de retourner le textfield du mail
     * @return TextField
     */
    public TextField tfMail() {
        return this.tfMail;
    }
    /**
     * Permet de retourner le bouton supprimer le compte
     * @return Button
     */
    public Button supprimerLeCompte() {
        return this.btnSupprimerCompte;
    }
    /**
     * Permet de créer l'espace informations utilisateur
     * @return Button
     */
    public VBox informationsUtilisateur() {
        this.lesInfos = new VBox();
        GridPane infos = new GridPane();
        BorderPane contientSuppr = new BorderPane();
        contientSuppr.setRight(this.supprimerLeCompte());
        contientSuppr.setMaxWidth(1055);
        contientSuppr.setPadding(new Insets(0, 0, 10, 0));
        infos.setVgap(10);
        infos.setHgap(20);
        infos.setPadding(new Insets(10));
        infos.add(this.pseudo(), 0, 0, 5, 1);
        infos.add(this.tfPseudo(), 15, 0, 8, 1);
        infos.add(this.btnModifierPseudo, 38, 0, 1, 1);
        infos.add(this.mdp(), 0, 1, 5, 1);
        infos.add(this.tfMDP(), 15, 1, 8, 1);
        infos.add(this.btnModifierMDP, 38, 1, 1, 1);
        infos.add(this.Mail(), 0, 2, 5, 1);
        infos.add(this.tfMail(), 15, 2, 8, 1);
        infos.add(this.btnModifierMail, 38, 2, 1, 1);
        this.lesInfos.setStyle("-fx-background-color: #D3D3D3; -fx-background-radius: 20px; -fx-opacity: 0.8; ");
        this.lesInfos.getChildren().addAll(infos, contientSuppr);
        this.lesInfos.setSpacing(10);
        this.lesInfos.setAlignment(Pos.CENTER);
        return this.lesInfos;
    }
    /**
     * Permet de créer le centre gauche de la page
     * @return VBox
     */
    public VBox centreCoteGauche() {
        VBox centre = new VBox();
        centre.setSpacing(10);
        centre.setPadding(new Insets(0, 0, 0, 100));
        Text titreCentre = new Text("Vos informations personnelles");
        titreCentre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));
        centre.getChildren().addAll(titreCentre, this.informationsUtilisateur());
        centre.setAlignment(Pos.CENTER);
        centre.setPrefHeight(300);
        return centre;
    }
    /**
     * Permet de créer le côté gauche de la page
     * @return BorderPane
     */
    public BorderPane coteGauchePage() {
        this.coteGauche.setCenter(this.centreCoteGauche());
        this.coteGauche.setTop(this.hautCoteGauche());
        this.coteGauche.setPrefWidth(1200);
        return this.coteGauche;
    }
    /**
     * Permet de créer un affichage pour l'engrenage
     * @return HBox
     */
    public HBox contientEngrenage() {
        HBox contientEngrenage = new HBox();
        ImageView engrenage = new ImageView(new Image("file:./ressources/img/engrenage.png"));
        contientEngrenage.setAlignment(Pos.CENTER);
        contientEngrenage.getChildren().add(engrenage);
        engrenage.setFitHeight(80);
        engrenage.setFitWidth(80);
        contientEngrenage.setPadding(new Insets(25, 0, 0, 0));
        return contientEngrenage;
    }
    /**
     * Permet de créer l'emplacement pour les boutons su milieu
     * @return VBox
     */
    public VBox boutonsDuMilieu() {
        VBox boutons = new VBox();
        boutons.setSpacing(25);
        Button bouton1 = new Button("VOS ENCHERES");
        bouton1.setCursor(Cursor.HAND);
        bouton1.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 20px;");
        Button bouton2 = new Button("FAVORIS");
        bouton2.setCursor(Cursor.HAND);
        bouton2.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 20px;");
        Button bouton3 = new Button("VOS VENTES");
        bouton3.setCursor(Cursor.HAND);
        bouton3.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 20px;");
        Button bouton4 = new Button("PARAMETRES");
        bouton4.setCursor(Cursor.HAND);
        bouton4.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 20px;");
        boutons.getChildren().addAll(bouton1, bouton2, bouton3, bouton4);
        boutons.setPadding(new Insets(10));
        boutons.setAlignment(Pos.CENTER);
        return boutons;
    }
    /**
     * Permet de créer l'emplacement pour le bouton de déconnexion
     * @return HBox
     */
    public HBox deconnexion() {
        HBox contientDeconnexion = new HBox();
        contientDeconnexion.getChildren().add(this.btnDeconnexion);
        contientDeconnexion.setAlignment(Pos.CENTER);
        contientDeconnexion.setPadding(new Insets(10));
        return contientDeconnexion;
    }
    /**
     * Permet de créer le côté droit de la page
     * @return BorderPane
     */
    public BorderPane coteDroitPage() {
        this.coteDroit.setTop(this.contientEngrenage());
        this.coteDroit.setCenter(this.boutonsDuMilieu());
        this.coteDroit.setBottom(this.deconnexion());
        this.coteDroit.setPadding(new Insets(10));
        this.coteDroit.setPrefWidth(350);
        this.coteDroit.setStyle("-fx-background-color: #D3D3D3; -fx-background-radius: 10px; -fx-opacity: 0.8;");
        return this.coteDroit;
    }
    /**
     * Permet de créer la page
     */
    public void laPage() {
        this.setLeft(this.coteGauchePage());
        this.setRight(this.coteDroitPage());
        this.setStyle("-fx-background-image: url('file:./ressources/img/background3.png')");
    }
}
