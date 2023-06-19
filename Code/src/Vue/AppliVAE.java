package Vue;

import Controller.ControleQuitter;
import Controller.ControllerBtnActifUser;
import Controller.ControllerBtnConnexion;
import Controller.ControllerBtnCreerCompte;
import Controller.ControllerBtnDeconnexion;
import Controller.ControllerBtnDeleteUser;
import Controller.ControllerBtnEditUser;
import Controller.ControllerBtnFullscreen;
import Controller.ControllerBtnMenuAdmin;
import Controller.ControllerEnterCreerCompte;
import Controller.ControllerLienInscription;
import Controller.ControllerRechecherUsers;
import Controller.ControllerRetour;
import Controller.ControllerRetourAdmin;
import Modele.GestionVentes;
import Modele.Utilisateur;
import Modele.Vente;
import Vue.Administration.FenetreAdmin;
import Vue.Administration.FenetreGestionContrats;
import Vue.Administration.FenetreGestionEntreprise;
import Vue.Administration.FenetreGestionParametres;
import Vue.Administration.FenetreGestionSignalements;
import Vue.Administration.FenetreGestionUsers;
import Vue.Administration.FenetreGestionVentes;
import Controller.ControllerEnterLogin;
import Controller.ControllerEnterRechecherUsers;
import Controller.ControllerLastUserSelected;
import Modele.BD.ConnexionMySQL;
import Modele.BD.ConnexionUtilisateur;
import Modele.BD.GestionUtilisateurs;
import Modele.BD.InscriptionUtilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// autres imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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

    // edit users
    private TextField tfEditID;
    private TextField tfEditUsername;
    private TextField tfEditEmail;
    private ComboBox<String> cbEditRole;

    // Gestion Entreprise
    private Label lbNbTotUsers;
    private Label lbNbUsersActif;
    private Label lbNbUsersInactif;

    // Gestion Ventes
    private List<Vente> listVentes;
    private Label lbNbTotVentes;
    private Label lbNbVentesValidée;
    private Label lbNbVentesNonConclus;

    private Button btnSearchVente;
    private Button btnRefreshVente;
    private TextField tfSearchVente;
    private TableView<Vente> tableVentes;
    private Button btnDeleteVente;
    private ObservableList<Vente> listVentesRecherchees;
    private Vente derniereVenteSelected;
    private Button btnEditVente;

    private GestionVentes gestionVentes;

    //

    private AppliVAE appliVAE;
    
    private Scene scene;
    private ConnexionMySQL connection;
    private ConnexionUtilisateur connexionUtilisateur;
    private InscriptionUtilisateur inscriptionUtilisateur;
    private GestionUtilisateurs gestionUsers;
    private Utilisateur utilisateur;
    private Stage stage;

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
        this.gestionVentes= new GestionVentes();

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

        this.btnRetour = new Button("RETOUR");
        this.btnRetour.setOnAction(new ControllerRetour(this));

        this.btnCreerCompte = new Button("CRÉER UN COMPTE"); // fini de remplir le formulaire d'inscription
        this.btnCreerCompte.setOnAction(new ControllerBtnCreerCompte(this,this.inscriptionUtilisateur,this.utilisateur,this.gestionUsers));

        //fenetre Accueil
        this.btnDeconnexion = new Button("DÉCONNEXION");
        this.btnDeconnexion.setOnAction(new ControllerBtnDeconnexion(this));

        //fenetre Admin
        ControllerBtnMenuAdmin controllerBtnGestionAdmin = new ControllerBtnMenuAdmin(this,this.gestionUsers);

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

        // fenêtre Gestion Users
        this.btnSearch = new Button("OK");
        this.btnRefresh = new Button("Actualiser");
        this.listUtilisateursRecherchés = FXCollections.observableArrayList();
        this.btnSearch.setOnAction(new ControllerRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.btnRefresh.setOnAction(new ControllerRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.tfSearch = new TextField("");
        this.tfSearch.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers));
        this.table = new TableView<>();
        this.table.setOnMouseClicked(new ControllerLastUserSelected(this, table));
        this.btnDeleteUser = new Button("Supprimer l'utilisateur");
        this.btnDeleteUser.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
        this.btnEdit = new Button("Modifier l'utilisateur");
        this.btnEdit.setOnAction(new ControllerBtnEditUser(this, gestionUsers));

        this.tfEditID = new TextField();
        this.tfEditUsername = new TextField();
        this.tfEditEmail = new TextField();
        this.cbEditRole = new ComboBox<>();
        
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
        this.btnSearchVente.setOnAction(new ControllerRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.btnRefreshVente.setOnAction(new ControllerRechecherUsers(this,this.gestionUsers,this.listUtilisateursRecherchés));
        this.tfSearchVente = new TextField("");
        this.tfSearchVente.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers));
        this.tableVentes = new TableView<>();
        this.tableVentes.setOnMouseClicked(new ControllerLastUserSelected(this, table));
        this.btnDeleteVente = new Button("Supprimer l'utilisateur");
        this.btnDeleteVente.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
        this.btnEditVente = new Button("Modifier l'utilisateur");
        this.btnEditVente.setOnAction(new ControllerBtnEditUser(this, gestionUsers));

        // this.tfEditID = new TextField();
        // this.tfEditUsername = new TextField();
        // this.tfEditEmail = new TextField();
        // this.cbEditRole = new ComboBox<>();
    }

        @Override
        public void start(Stage stage) {
            this.stage = stage;
            this.btnFullscreen.setOnAction(new ControllerBtnFullscreen(this.stage));
            // Création de la première fenêtre de connexion
            // Pane root = new FenetreConnexion(this.btnConnexion, this.btnQuitter, this.btnLienInscription, this.username, this.password,this.passwordMontrer,this.showPassword,this.btnFullscreen);
            
            TableColumn<Utilisateur, Boolean> isActifCol = new TableColumn<>("Actif");
            isActifCol.setPrefWidth(80);
            isActifCol.setCellValueFactory(new PropertyValueFactory<>("actif"));
            setTableButtonAction(isActifCol);

            Pane root = new FenetreGestionUsers(this.btnDeconnexion,this.btnRetourAdmin,this.tfSearch,this.btnSearch,this.table,this.gestionUsers,isActifCol,this.btnDeleteUser,this.btnRefresh,this.btnEdit);
            this.scene = new Scene(root, 1080, 720);
            this.scene.getStylesheets().add("file:./ressources/css/styles.css");
            this.stage.setScene(scene);
            this.stage.setFullScreen(true);
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

            Pane root = new FenetreInscription(this.btnRetour, this.btnCreerCompte, this.username2,this.email,this.password2, this.confirmPassword,this.btnQuitter);
            this.scene.setRoot(root);

            // Déplacer la logique du focus (éviter d'afficher le curseur dans le texfield, se qui cache le texte en background)
            Platform.runLater(() -> {
                this.btnCreerCompte.requestFocus();
            });
        }
        
        public void afficheFenetreAccueil() {
            // Affichage de la fenêtre d'accueil
            Pane root = new FenetreAccueil(this.btnDeconnexion,this.utilisateur);
            this.scene.setRoot(root);
        }

        public void afficheFenetreAdmin() {
            // Affichage de la fenêtre d'accueil
            Pane root = new FenetreAdmin(this.btnDeconnexion,this.btnGestionUsers,this.btnGestionSignalements,btnGestionVentes,btnGestioContrats,btnGestionEntreprise,btnGestionParamètres);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionUsers() {
            this.btnDeleteUser = new Button("Supprimer l'utilisateur");
            this.btnDeleteUser.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
            this.table = new TableView<>();
            this.table.setOnMouseClicked(new ControllerLastUserSelected(this, table));
            this.tfSearch = new TextField("");
            this.tfSearch.setPromptText("Rechercher :");
            this.tfSearch.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers));
            this.btnEdit = new Button("Modifier");
            this.btnEdit.setOnAction(new ControllerBtnEditUser(this,this.gestionUsers));

            TableColumn<Utilisateur, Boolean> isActifCol = new TableColumn<>("Actif");
            isActifCol.setPrefWidth(80);
            isActifCol.setCellValueFactory(new PropertyValueFactory<>("actif"));
            setTableButtonAction(isActifCol);
            
            Pane root = new FenetreGestionUsers(this.btnDeconnexion,this.btnRetourAdmin,this.tfSearch,this.btnSearch,this.table,this.gestionUsers,isActifCol,this.btnDeleteUser,this.btnRefresh,this.btnEdit);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionSignalements() {
            Pane root = new FenetreGestionSignalements(this.btnDeconnexion,this.btnRetourAdmin);
            this.scene.setRoot(root);
        }

        public void afficheFenetreGestionVentes() {
            this.btnDeleteVente = new Button("Supprimer l'utilisateur");
            this.btnDeleteVente.setOnAction(new ControllerBtnDeleteUser(this, gestionUsers));
            this.tableVentes = new TableView<>();
            this.tableVentes.setOnMouseClicked(new ControllerLastUserSelected(this, table));
            this.tfSearchVente = new TextField("");
            this.tfSearchVente.setPromptText("Rechercher :");
            this.tfSearchVente.setOnKeyReleased(new ControllerEnterRechecherUsers(this,this.gestionUsers));
            this.btnEditVente = new Button("Modifier");
            this.btnEditVente.setOnAction(new ControllerBtnEditUser(this,this.gestionUsers));

            TableColumn<Vente, Boolean> isActifColVente = new TableColumn<>("Actif");
            isActifColVente.setPrefWidth(80);
            isActifColVente.setCellValueFactory(new PropertyValueFactory<>("actif"));
            
            Pane root = new FenetreGestionVentes(this.btnDeconnexion,this.btnRetourAdmin,this.tfSearchVente,this.btnSearchVente,this.tableVentes,this.gestionVentes,isActifColVente,this.btnDeleteVente,this.btnRefreshVente,this.btnEditVente);
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
                Label role = new Label("Role : " + userSelected.getNomRole());

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
                Label role = new Label("Role : " + userSelected.getNomRole());

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
            if(userSelected.getNomRole().equals("Administrateur"))
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
                                Utilisateur utilisateur = getTableRow().getItem();
                                button.setOnAction(new ControllerBtnActifUser(appliVAE, utilisateur, gestionUsers, table));
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

        public void quitte() {
            // Fermeture de l'application
            Platform.exit();
        }

        
}