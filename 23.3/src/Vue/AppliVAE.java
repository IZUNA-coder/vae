package Vue;

import Controller.*;
import Modele.*;
import Modele.BD.*;
import Vue.Administration.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
// autres imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class AppliVAE extends Application {

    // Composants de l'interface utilisateur
    private Button btnConnexion;
    private Button btnQuitter;
    private Button btnLienInscription;
    private TextField username;
    private PasswordField password;
    private TextField passwordMontrer;
    private CheckBox showPassword;
    private Button btnFullscreen;

    private Button btnCreerCompte;
    private Button btnRetour;
    private TextField username2;
    private TextField email;
    private PasswordField password2;
    private PasswordField confirmPassword;

    private Button btnDeconnexion;
    private Button btnGestionUsers;
    private Button btnGestionSignalements;
    private Button btnGestionVentes;
    private Button btnGestioContrats;
    private Button btnGestionEntreprise;
    private Button btnGestionParamètres;
    private Button btnRetourAdmin;

    // Gestion Users
    private Button btnSearch;
    private Button btnRefresh;
    private TextField tfSearch;
    private TableView<Utilisateur> table;
    private Button btnDeleteUser;
    private ObservableList<Utilisateur> listUtilisateursRecherchés;
    private Utilisateur dernierUserSelected;
    private Button btnEdit;
    private Button btnAddUser;

    // edit users
    private TextField tfEditID;
    private TextField tfEditUsername;
    private TextField tfEditEmail;
    private ComboBox<String> cbEditRole;

    // add users
    private TextField tfAddUsername;
    private TextField tfAddEmail;
    private PasswordField pfAddPassword;
    private ComboBox<String> cbAddRole;

    // Gestion Entreprise
    private Label lbNbTotUsers;
    private Label lbNbUsersActif;
    private Label lbNbUsersInactif;

    // Gestion Ventes
    private Label lbNbTotVentes;
    private Label lbNbVentesValidée;
    private Label lbNbVentesNonConclus;

    private Button btnSearchVente;
    private Button btnRefreshVente;
    private TextField tfSearchVente;
    private TableView<Vente> tableVentes;
    private Button btnDeleteVente;
    private Button btnEditVente;
    private ObservableList<Vente> listVentesRecherchees;
    // private Vente derniereVenteSelected;

    private GestionVentes gestionVentes;

    //
    private Hyperlink hyperlinkAccueil;
    private Button profilVendeur;
    private PhotoBD photoBD;

    private Button btnVAE;
    private Button btnProfil;
    private Button btnVendre;


    //

    private AppliVAE appliVAE;
    private List<Vente> listVentes;
    
    private Scene scene;
    private ConnexionMySQL connection;
    private ConnexionUtilisateur connexionUtilisateur;
    private InscriptionUtilisateur inscriptionUtilisateur;
    private GestionUtilisateurs gestionUsers;
    private Utilisateur utilisateur;
    private Stage stage;

    private Button btnRetourConnexion;
    private String recherche;
    private TextField rechercheBar = new TextField();
    public static void main(String[] args) {
        launch(args);
    }

    public void afficherPopUpErreur(boolean error, String titre, String text, String autreText){
        // Afficher une alerte en cas d'échec de connexion
        Alert alert;
        if(error){
            alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
        } else {alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);}
        alert.initOwner(stage);
        alert.setTitle(titre);
        alert.setHeaderText(text);
        alert.setContentText(autreText);
        alert.showAndWait();
    }

    public boolean confirmationPopUp(String headerText, String contentText) {
        // Créer une alerte de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Confirmation");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
    
        // Obtenir la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
    
        // Vérifier si l'utilisateur a cliqué sur le bouton OK
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public Connection getConnection(){
        return this.connection.getConnection();
    }

    public void afficherChargement() {
        // Créer une boîte de dialogue modale
        Alert loadingDialog = new Alert(Alert.AlertType.INFORMATION);
        loadingDialog.setTitle("Chargement en cours");
        loadingDialog.setHeaderText("Chargement des données...");
        loadingDialog.setContentText("Veuillez patienter...");
        loadingDialog.initOwner(stage);

        // Créer une barre de progression
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);

        // Ajouter la barre de progression à la boîte de dialogue
        VBox dialogPane = new VBox(10, progressBar);
        dialogPane.setAlignment(Pos.CENTER);
        loadingDialog.getDialogPane().setContent(dialogPane);

        // Afficher la boîte de dialogue
        loadingDialog.show();

        // Créer une animation de chargement pendant 3 secondes
        Duration duration = Duration.seconds(3);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(duration, event -> {
                    System.out.println("Chargement terminé");
                    // Fermer la boîte de dialogue après 3 secondes
                    loadingDialog.close();
                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.play();
    }

    @Override
    public void init() {
        try {
            this.connection = new ConnexionMySQL();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Initialisation des composants de l'interface utilisateur
        this.appliVAE = new AppliVAE();
        this.utilisateur = new Utilisateur();
        this.connexionUtilisateur = new ConnexionUtilisateur(utilisateur, getConnection());
        this.inscriptionUtilisateur = new InscriptionUtilisateur(utilisateur);
        this.gestionUsers = new GestionUtilisateurs(getConnection());
        this.gestionVentes= new GestionVentes(getConnection());
        try {
            this.gestionVentes.getVente();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.btnRetour = new Button("RETOUR");
        this.btnRetour.setCursor(Cursor.HAND);
        this.btnRetour.setId("btnRetour");
        this.btnRetour.setOnAction(new ControllerRetour(this));

        //fenetre Connexion

        this.btnConnexion = new Button("CONNEXION");
        this.btnQuitter = new Button("QUITTER");
        this.btnLienInscription = new Button("S'INSCRIRE"); // créer un compte

        this.btnConnexion.setOnAction(new ControllerBtnConnexion(this,this.connexionUtilisateur,this.utilisateur));
        this.btnQuitter.setOnAction(new ControleQuitter(this));
        this.btnLienInscription.setOnAction(new ControllerLienInscription(this));

        this.username = new TextField("");
        this.username.setPromptText("Identifiant");
        this.password = new PasswordField();
        this.password.setPromptText("Mot de passe");
        this.passwordMontrer = new TextField();
        this.passwordMontrer.setPromptText("Mot de passe");
        this.passwordMontrer.setVisible(false);

        this.username.setOnKeyReleased(new ControllerEnterLogin(this,this.connexionUtilisateur,this.utilisateur));
        this.password.setOnKeyReleased(new ControllerEnterLogin(this,this.connexionUtilisateur,this.utilisateur));
        this.passwordMontrer.setOnKeyReleased(new ControllerEnterLogin(this,this.connexionUtilisateur,this.utilisateur));

        this.showPassword = new CheckBox("Afficher le mot de passe");
        this.showPassword.setCursor(Cursor.HAND);

        this.btnFullscreen = new Button();
        this.btnFullscreen.setCursor(Cursor.HAND);

        //fenetre Inscription

        this.btnRetourConnexion = new Button("RETOUR");
        this.btnRetourConnexion.setCursor(Cursor.HAND);
        this.btnRetourConnexion.setId("btnRetour");
        this.btnRetourConnexion.setOnAction(new ControllerRetourConnexion(this));

        this.btnCreerCompte = new Button("CRÉER UN COMPTE"); // fini de remplir le formulaire d'inscription
        this.btnCreerCompte.setOnAction(new ControllerBtnCreerCompte(this,this.inscriptionUtilisateur,this.utilisateur,this.gestionUsers));

        //fenetre Accueil
        this.btnDeconnexion = new Button("DÉCONNEXION");
        this.btnDeconnexion.setOnAction(new ControllerBtnDeconnexion(this));

        //fenetre Admin
        ControllerBtnMenuAdmin controllerBtnGestionAdmin = new ControllerBtnMenuAdmin(this,this.gestionUsers,this.gestionVentes);

        this.btnGestionUsers = new Button(" Utilisateurs");
        this.btnGestionUsers.setOnAction(controllerBtnGestionAdmin);

        this.btnGestionSignalements = new Button(" Signalements");
        this.btnGestionSignalements.setOnAction(controllerBtnGestionAdmin);

        this.btnGestionVentes = new Button(" Ventes");
        this.btnGestionVentes.setOnAction(controllerBtnGestionAdmin);

        this.btnGestioContrats = new Button(" Contrats");
        this.btnGestioContrats.setOnAction(controllerBtnGestionAdmin);

        this.btnGestionEntreprise = new Button(" Entreprise");
        this.btnGestionEntreprise.setOnAction(controllerBtnGestionAdmin);

        this.btnGestionParamètres = new Button(" Paramètres");
        this.btnGestionParamètres.setOnAction(controllerBtnGestionAdmin);

        this.btnRetourAdmin = new Button("RETOUR");
        this.btnRetourAdmin.setOnAction(new ControllerRetourAdmin(this));

        this.btnVAE = new Button("VAE");
        this.btnVAE.setOnAction(new ControllerBtnVAE(this));
        ImageView imgProfil = new ImageView(new Image("file:ressources/img/PageAccueil/imgprofile.png"));
        this.btnProfil = new Button(null, imgProfil);
        imgProfil.setFitHeight(50);
        imgProfil.setFitWidth(50);
        this.btnProfil.setOnAction(new ControllerBtnProfil(this));
        this.btnVAE.setCursor(Cursor.HAND);
        this.btnProfil.setCursor(Cursor.HAND);

        ImageView imgVendre = new ImageView(new Image("file:ressources/img/PageAccueil/btnVendre.png"));
        this.btnVendre = new Button(null, imgVendre);
        imgVendre.setFitHeight(50);
        imgVendre.setFitWidth(50);
        this.btnVendre.setOnAction(new ControllerBtnVendre(this));
        this.btnVendre.setCursor(Cursor.HAND);


        // fenêtre Gestion Users
        this.btnSearch = new Button("OK");
        this.btnRefresh = new Button("Actualiser");
        this.listUtilisateursRecherchés = FXCollections.observableArrayList();
        this.btnSearch.setOnAction(new ControllerRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.btnRefresh.setOnAction(new ControllerRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.tfSearch = new TextField("");
        this.tfSearch.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.table = new TableView<>();
        this.table.setOnMouseClicked(new ControllerLastUserSelected(this, table));
        this.btnDeleteUser = new Button("Supprimer l'utilisateur");
        this.btnDeleteUser.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
        this.btnEdit = new Button("Modifier l'utilisateur");
        this.btnEdit.setOnAction(new ControllerBtnEditUser(this, gestionUsers));
        this.btnAddUser = new Button("Ajouter un utilisateur");
        this.btnAddUser.setOnAction(new ControllerBtnAddUser(this, gestionUsers));

        this.tfEditID = new TextField();
        this.tfEditUsername = new TextField();
        this.tfEditEmail = new TextField();
        this.cbEditRole = new ComboBox<>();

        this.tfAddUsername = new TextField();
        this.tfAddEmail = new TextField();
        this.pfAddPassword = new PasswordField();
        this.pfAddPassword.setPromptText("Mot de passe");
        this.cbAddRole = new ComboBox<>();
        
        // fenêtre Gestion Entreprise
        this.lbNbTotUsers = new Label("10");
        this.lbNbUsersActif = new Label("8");
        this.lbNbUsersInactif = new Label("2"); 
        
        // fenêtre Gestion Ventes
        this.lbNbTotVentes = new Label("10");
        this.lbNbVentesValidée = new Label("8");
        this.lbNbVentesNonConclus = new Label("2");

        this.btnSearchVente = new Button("OK");
        this.btnRefreshVente = new Button("Actualiser");
        this.listVentesRecherchees = FXCollections.observableArrayList();
        this.btnSearchVente.setOnAction(new ControllerRechecheVentes(this,this.gestionVentes,this.listVentesRecherchees));
        this.btnRefreshVente.setOnAction(new ControllerRechecheVentes(this,this.gestionVentes,this.listVentesRecherchees));
        this.tfSearchVente = new TextField("");
        // this.tfSearchVente.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers));
        this.tableVentes = new TableView<>();
        // this.tableVentes.setOnMouseClicked(new ControllerLastUserSelected(this, table));
        this.btnDeleteVente = new Button("Supprimer l'utilisateur");
        // this.btnDeleteVente.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
        this.btnEditVente = new Button("Modifier l'utilisateur");
        // this.btnEditVente.setOnAction(new ControllerBtnEditUser(this, gestionUsers));

        // this.tfEditID = new TextField();
        // this.tfEditUsername = new TextField();
        // this.tfEditEmail = new TextField();
        // this.cbEditRole = new ComboBox<>();
    
        this.hyperlinkAccueil = new Hyperlink("Accueil");// créer une hyperlien
        this.hyperlinkAccueil.setOnAction(new ControllerBtnAccueil(this));

        this.profilVendeur = new Button("Profil Vendeur");
        this.profilVendeur.setOnAction(new ControllerBtnProfilVendeur(this));
        
        this.photoBD = new PhotoBD(getConnection());
        this.listVentes = new ArrayList<>();
        
        this.recherche = "";

        try {
            this.listVentes = gestionVentes.getVenteRecherche("");
            System.out.println("Liste des ventes : " + this.listVentes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Vente v : this.listVentes){
            this.photoBD.setPhoto(v);
        }
    }

        @Override
        public void start(Stage stage) {
            this.stage = stage;
            this.btnFullscreen.setOnAction(new ControllerBtnFullscreen(this.stage));
            // Création de la première fenêtre de connexion
            Pane root = new FenetreConnexion(this.btnConnexion, this.btnQuitter, this.btnLienInscription, this.username, this.password,this.passwordMontrer,this.showPassword,this.btnFullscreen);
            
            TableColumn<Utilisateur, Boolean> isActifCol = new TableColumn<>("Actif");
            isActifCol.setPrefWidth(80);
            isActifCol.setCellValueFactory(new PropertyValueFactory<>("actif"));
            setTableButtonAction(isActifCol);

            // Pane root = new FenetreGestionUsers(this.btnDeconnexion,this.btnRetourAdmin,this.tfSearch,this.btnSearch,this.table,this.gestionUsers,isActifCol,this.btnDeleteUser,this.btnRefresh,this.btnEdit,this.btnAddUser);
            // Pane root = new FenetreAccueil(this,utilisateur, gestionVentes, btnVAE, btnProfil, photoBD);
            this.scene = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

            this.scene.getStylesheets().add("file:./ressources/css/styles.css");
            this.stage.setScene(scene);
            this.stage.setMaximized(true);
            this.stage.centerOnScreen();
            stage.getIcons().add(new Image("file:ressources/img/sells.png"));
            this.stage.setTitle("Appli VAE");
            this.stage.show();


            // Déplacer la logique du focus (éviter d'afficher le curseur dans le texfield, se qui cache le texte en background)
            Platform.runLater(() -> {
                this.btnConnexion.requestFocus();
            });
        }
        
        public void afficheFenetreConnexion() {
            // Affichage de la première fenêtre de connexion
            this.username = new TextField("");
            this.username.setPromptText("Identifiant");
            this.password = new PasswordField();
            this.password.setPromptText("Mot de passe");
            this.passwordMontrer = new TextField();
            this.passwordMontrer.setPromptText("Mot de passe");
            this.passwordMontrer.setVisible(false);

            this.username.setOnKeyReleased(new ControllerEnterLogin(this,this.connexionUtilisateur,this.utilisateur));
            this.password.setOnKeyReleased(new ControllerEnterLogin(this,this.connexionUtilisateur,this.utilisateur));
            this.passwordMontrer.setOnKeyReleased(new ControllerEnterLogin(this,this.connexionUtilisateur,this.utilisateur));

            this.showPassword = new CheckBox("Afficher le mot de passe");
            this.showPassword.setCursor(Cursor.HAND);
            
            

            Pane root = new FenetreConnexion(this.btnConnexion, this.btnQuitter, this.btnLienInscription, this.username, this.password,this.passwordMontrer,this.showPassword,this.btnFullscreen);
            this.scene.setRoot(root);

            // Déplacer la logique du focus (éviter d'afficher le curseur dans le texfield, se qui cache le texte en background)
            Platform.runLater(() -> {
                this.btnConnexion.requestFocus();
            });

            
        }
        
        public void afficheFenetreInscription() {
            // Affichage de la fenêtre d'inscription
            this.username2 = new TextField("");
            this.username2.setPromptText("Identifiant");
            this.email = new TextField("");
            this.email.setPromptText("Email");
            this.password2 = new PasswordField();
            this.password2.setPromptText("Mot de passe");
            this.confirmPassword = new PasswordField();
            this.confirmPassword.setPromptText("Valider votre mot de passe");

            this.username2.setOnKeyReleased(new ControllerEnterCreerCompte(this,this.inscriptionUtilisateur,this.utilisateur,this.gestionUsers));
            this.password2.setOnKeyReleased(new ControllerEnterCreerCompte(this,this.inscriptionUtilisateur,this.utilisateur,this.gestionUsers));
            this.confirmPassword.setOnKeyReleased(new ControllerEnterCreerCompte(this,this.inscriptionUtilisateur,this.utilisateur,this.gestionUsers));

            Pane root = new FenetreInscription(this.btnRetourConnexion, this.btnCreerCompte, this.username2,this.email,this.password2, this.confirmPassword,this.btnQuitter);
            this.scene.setRoot(root);

            // Déplacer la logique du focus (éviter d'afficher le curseur dans le texfield, se qui cache le texte en background)
            Platform.runLater(() -> {
                this.btnCreerCompte.requestFocus();
            });
        }
        
        public void afficheFenetreTest() {
            // Affichage de la fenêtre de test (accueil)
            Pane root = new FenetreTest(this.btnDeconnexion,this.utilisateur,this.profilVendeur);
            this.scene.setRoot(root);
        }

        public void afficheFenetreAccueil(){
            // Affichage de la fenêtre d'accueil

            Pane root = new FenetreAccueil(this,this.utilisateur,this.gestionVentes,this.btnVAE,this.btnProfil,this.photoBD,this.btnVendre);
            this.scene.setRoot(root);
        }

        public void afficheFenetreAdmin() {
            // Affichage de la fenêtre d'accueil
            Pane root = new FenetreAdmin(this.btnDeconnexion,this.btnGestionUsers,this.btnGestionSignalements,btnGestionVentes,btnGestioContrats,btnGestionEntreprise,btnGestionParamètres);
            this.scene.setRoot(root);
        }

        public void afficheFenetreVendre() {
            // Affichage de la fenêtre d'accueil
            Pane root = new FenetreVendre(this.btnProfil,this.btnVAE,this.btnRetour);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionUsers() {
            this.table = new TableView<>();
            this.table.setOnMouseClicked(new ControllerLastUserSelected(this, table));
            this.tfSearch = new TextField("");
            this.tfSearch.setPromptText("Rechercher :");
            this.tfSearch.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));

            TableColumn<Utilisateur, Boolean> isActifCol = new TableColumn<>("Actif");
            isActifCol.setPrefWidth(80);
            isActifCol.setCellValueFactory(new PropertyValueFactory<>("actif"));
            setTableButtonAction(isActifCol);

            try {
                this.listUtilisateursRecherchés.clear(); // Effacer les données précédentes
    
                for (Utilisateur user : this.gestionUsers.getUtilisateurs()) {
                    this.listUtilisateursRecherchés.add(user);
                }
                
                setUtilisateursTable(this.listUtilisateursRecherchés);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            Pane root = new FenetreGestionUsers(this.btnDeconnexion,this.btnRetourAdmin,this.tfSearch,this.btnSearch,this.table,this.gestionUsers,isActifCol,this.btnDeleteUser,this.btnRefresh,this.btnEdit,this.btnAddUser);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionSignalements() {
            Pane root = new FenetreGestionSignalements(this.btnDeconnexion,this.btnRetourAdmin);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionVentes() {
            this.btnDeleteVente = new Button("Supprimer la vente");
            this.btnDeleteVente.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
            this.tableVentes = new TableView<>();
            this.tableVentes.setOnMouseClicked(new ControllerLastUserSelected(this, table));
            this.tfSearchVente = new TextField("");
            this.tfSearchVente.setPromptText("Rechercher :");
            this.tfSearchVente.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
            this.btnEditVente = new Button("Modifier");
            this.btnEditVente.setOnAction(new ControllerBtnEditUser(this,this.gestionUsers));
        
            try {
                this.listVentesRecherchees.clear(); // Effacer les données précédentes

                for (Vente vente : this.gestionVentes.getVente()) {
                    this.listVentesRecherchees.add(vente);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            setVentesTable(this.listVentesRecherchees);
            
            Pane root = new FenetreGestionVentes(this.btnDeconnexion,this.btnRetourAdmin,this.tfSearchVente,this.btnSearchVente,this.tableVentes,this.gestionVentes,this.btnDeleteVente,this.btnRefreshVente,this.btnEditVente);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionContrats() {
            Pane root = new FenetreGestionContrats(this.btnDeconnexion,this.btnRetourAdmin);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionEntreprise() {
            Pane root = new FenetreGestionEntreprise(this.btnDeconnexion,this.btnRetourAdmin,this.lbNbTotUsers,this.lbNbUsersActif,this.lbNbUsersInactif);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionParametres() {
            Pane root = new FenetreGestionParametres(this.btnDeconnexion,this.btnRetourAdmin);
            this.scene.setRoot(root);
        }

        public void afficheFenetreRechercheEnchere(String newValue){
            this.rechercheBar = new TextField();
            Pane root = new FenetreRechercheEnchere(this,this.btnRetour, this.btnDeconnexion,this.gestionVentes,this.photoBD,rechercheBar,this.btnVendre,this.btnProfil,this.btnVAE);
            this.scene.setRoot(root);
            this.rechercheBar.setText(newValue);
            remettreLeFocusNavBar();
            
        }

        public void remettreLeFocusNavBar(){
            this.rechercheBar.requestFocus();
            this.rechercheBar.positionCaret(this.rechercheBar.getText().length());
        }

        public void afficheFenetreProfil(){
            Pane root = new FenetreProfil(this.btnVAE);
            this.scene.setRoot(root);
        }

        public void afficheFenetreObjAVendre(Vente v){
            Pane root = new FenetreObjAVendre(this.btnRetour, v);
            this.scene.setRoot(root);
        }

        public boolean afficherPopUpSupprimerUser(Utilisateur userSelected){
            // Afficher une alerte de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(stage);
            alert.setTitle("Gestion Users");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimé le compte de l'utilisateur ? (cela supprimera aussi toutes ces enchères et ces ventes d'objets)");
            VBox infoUsers = new VBox();
            infoUsers.setPadding(new Insets(10, 10, 10, 10));

            if(userSelected!=null){
                Label id = new Label("ID : " + userSelected.getId());
                Label username = new Label("Username : " + userSelected.getUsername());
                Label email = new Label("Email : " + userSelected.getEmail());
                Label role = new Label("Role : " + userSelected.getRole().getNomRole());

                infoUsers.getChildren().addAll(id, username, email, role);
                alert.getDialogPane().setContent(infoUsers);
            }

            Optional<ButtonType> result = alert.showAndWait();
    
            // Vérifier si l'utilisateur a cliqué sur le bouton OK
            return result.isPresent() && result.get() == ButtonType.OK;
        }

        public boolean afficheFenetreEditUser(Utilisateur userSelected) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(stage);
            alert.setTitle("Gestion Users");

            VBox infoUsers = new VBox();
            infoUsers.setPadding(new Insets(10, 10, 10, 10));

            if(userSelected!=null){
                Label id = new Label("ID : " + userSelected.getId());
                Label username = new Label("Username : " + userSelected.getUsername());
                Label email = new Label("Email : " + userSelected.getEmail());
                Label role = new Label("Role : " + userSelected.getRole().getNomRole());

                infoUsers.getChildren().addAll(id, username, email, role);
            } else{
                infoUsers.getChildren().addAll(new Label("Aucun utilisateur selectionné"));
            }

            

            alert.getDialogPane().setHeader(infoUsers);

            // textField pour changer l'id
            this.tfEditID = new TextField();
            this.tfEditID.setPromptText("Nouvel ID");

            // textField pour changer l'username
            this.tfEditUsername = new TextField();
            this.tfEditUsername.setPromptText("Nouvel username");

            // textField pour changer l'email
            this.tfEditEmail = new TextField();
            this.tfEditEmail.setPromptText("Nouvel email");

            // ComboBox pour le changement de role
            this.cbEditRole = new ComboBox<>();
            this.cbEditRole.getItems().addAll("Administrateur", "Utilisateur");
            if(userSelected.getRole().getNomRole().equals("Administrateur"))
                this.cbEditRole.setValue("Administrateur");
            else{
                this.cbEditRole.setValue("Utilisateur");
            }

            // ajouter la combobox a la fenetre de dialogue
            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(10);
            grid.add(new Label("Nouvel ID :"), 0, 0);
            grid.add(this.tfEditID, 1, 0);
            grid.add(new Label("Nouvel username :"), 0, 1);
            grid.add(this.tfEditUsername, 1, 1);
            grid.add(new Label("Nouvel email :"), 0, 2);
            grid.add(this.tfEditEmail, 1, 2);
            grid.add(new Label("Nouveau role :"), 0, 3);
            grid.add(this.cbEditRole, 1, 3);

            alert.getDialogPane().setContent(grid);
        
            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
        
            // Vérifier si l'utilisateur a cliqué sur le bouton OK
            return result.isPresent() && result.get() == ButtonType.OK;

        }

        public boolean afficheFenetreAddUser() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(stage);
            alert.setTitle("Gestion Users");

            VBox infoUsers = new VBox();
            infoUsers.setPadding(new Insets(10, 10, 10, 10));

            infoUsers.getChildren().addAll(new Label("Ajouter un utilisateur"));

            alert.getDialogPane().setHeader(infoUsers);

            // textField pour changer l'username
            this.tfAddUsername = new TextField();
            this.tfAddUsername.setPromptText("Username");

            // textField pour changer l'email
            this.tfAddEmail = new TextField();
            this.tfAddEmail.setPromptText("Email");

            // ComboBox pour le changement de role
            this.cbAddRole = new ComboBox<>();
            this.cbAddRole.getItems().addAll("Administrateur", "Utilisateur");
            this.cbAddRole.setValue("Utilisateur");

            this.pfAddPassword = new PasswordField();
            this.pfAddPassword.setPromptText("Mot de passe");

            // ajouter la combobox a la fenetre de dialogue
            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(10);
            grid.add(new Label("Nom d'utilisateur :"), 0, 0);
            grid.add(this.tfAddUsername, 1, 0);
            grid.add(new Label("Email :"), 0, 1);
            grid.add(this.tfAddEmail, 1, 1);
            grid.add(new Label("Password :"), 0, 2);
            grid.add(this.pfAddPassword, 1, 2);
            grid.add(new Label("Role :"), 0, 3);
            grid.add(this.cbAddRole, 1, 3);

            alert.getDialogPane().setContent(grid);
        
            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
        
            // Vérifier si l'utilisateur a cliqué sur le bouton OK
            return result.isPresent() && result.get() == ButtonType.OK;
        }

        public String getAddUsername(){
            return this.tfAddUsername.getText().toString();
        }

        public String getAddEmail(){
            return this.tfAddEmail.getText().toString();
        }

        public Role getAddRole(){
            if(this.cbAddRole.getValue().toString().equals("Administrateur")){
                return new Role("Administrateur");
            }
            else{
                return new Role("Utilisateur");
            }
        }

        public String getAddPassword(){
            return this.pfAddPassword.getText().toString();
        }

        public String getEditID(){
            return this.tfEditID.getText().toString();
        }

        public String getEditUsername(){
            return this.tfEditUsername.getText().toString();
        }

        public String getEditEmail(){
            return this.tfEditEmail.getText().toString();
        }

        public String getEditRole(){
            return this.cbEditRole.getValue().toString();
        }

        // fenêtre Connexion
        public String getUsername() {
            return this.username.getText().toString();
        }
        
        public String getPassword() {
            if(this.showPassword.isSelected()){
                return this.passwordMontrer.getText().toString();
            }
            else{
                return this.password.getText().toString();
            }
        }

        // fenêtre Inscription
        public String getUsername2() {
            return this.username2.getText().toString();
        }
        
        public String getEmail() {
            return this.email.getText().toString();
        }

        public String getPassword2() {
            return this.password2.getText().toString();
        }
        
        public String getConfirmPassword() {
            return this.confirmPassword.getText().toString();
        }

        // fenêtre Gestion Users
        public String getSearch(){
            return this.tfSearch.getText().toString();
        }

        public void setUtilisateursTable(ObservableList<Utilisateur> utilisateurs){
            this.table.setItems(utilisateurs);
        }

        public void setVentesTable(ObservableList<Vente> ventes){
            this.tableVentes.setItems(ventes);
        }

        public void effacerTable(){
            this.table.getItems().clear();
        }

        public Utilisateur getLastUserSelected(){
            return this.dernierUserSelected;
        }

        public void resetLastUserSelected(){
            this.dernierUserSelected=null;
        }
        
        public void updateLastSelectedUser(Utilisateur selectedUser) {
            this.dernierUserSelected=new Utilisateur();
            this.dernierUserSelected.setId(selectedUser.getId());
            this.dernierUserSelected.setUsername(selectedUser.getUsername());
            this.dernierUserSelected.setEmail(selectedUser.getEmail());
            this.dernierUserSelected.setPassword(selectedUser.getPassword());
            this.dernierUserSelected.setActif(selectedUser.getActif());
            this.dernierUserSelected.setRole(selectedUser.getRole());
            System.out.println(this.dernierUserSelected.getId());
        }

        public ObservableList<Utilisateur> getListUtilisateursRecherchés() {
            return this.listUtilisateursRecherchés;
        }

        // pour mettre un bouton dans une colonne, ici pour isActif.
        public void setTableButtonAction(TableColumn<Utilisateur, Boolean> col) {
            col.setCellFactory(new Callback<TableColumn<Utilisateur, Boolean>, TableCell<Utilisateur, Boolean>>() {
                @Override
                public TableCell<Utilisateur, Boolean> call(TableColumn<Utilisateur, Boolean> param) {
                    return new TableCell<Utilisateur, Boolean>() {
                        @Override
                        protected void updateItem(Boolean item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                Button button = new Button(item ? "Actif" : "Inactif");
                                button.setMinWidth(70);
                                setGraphic(button);
                                try{
                                    Utilisateur utilisateur = (Utilisateur) getTableRow().getItem();
                                    if (utilisateur != null) {
                                        button.setOnAction(new ControllerBtnActifUser(appliVAE, utilisateur, gestionUsers, table));
                                    }
                                }
                                catch(Exception e){
                                }
                            }
                        }
                    };
                }
            });
        }

        // Gestion Entreprise
        public void setNbUserLabel(String nbTot,String nbActifs,String nbInactifs){
            this.lbNbTotUsers.setText(nbTot);
            this.lbNbUsersActif.setText(nbActifs);
            this.lbNbUsersInactif.setText(nbInactifs);
        }
        
        public void setNbVentesLabel(String nbTotVentes, String nbVentesValidee, String nbVentesNonConclus) {
            this.lbNbTotVentes.setText(nbTotVentes);
            this.lbNbVentesValidée.setText(nbVentesValidee);
            this.lbNbVentesNonConclus.setText(nbVentesNonConclus);
        }

        public void afficheFenetreProfilVendeur(){
            Pane root = new FenetreProfilVendeur(this.hyperlinkAccueil);
            this.scene.setRoot(root);
        }

        public void setRecherche(String text) {
            this.recherche=text;
        }

        public String getRecherche() {
            return this.recherche;
        }

        public void quitte() {
            // Fermeture de l'application
            Platform.exit();
        }

        public Utilisateur getUtilisateur() {
            return this.utilisateur;
        }

        public void setUser(Utilisateur user) {
            this.utilisateur = user;
        }
}