package Vue.Administration;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.SelectionMode;


import java.util.ArrayList;
import java.util.List;

import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 
public class FenetreGestionUsers extends BorderPane {
    
    private Button btnDeconnexion;
    private Button btnRetourAdmin;
    private TextField tfSearch;
    private Button btnSearch;
    private Button btnRefresh;
    private Button btnEdit;
    private TableView<Utilisateur> table;
    private TableColumn<Utilisateur, Boolean> isActifCol;
    private Button btnDeleteUser;
    
    public FenetreGestionUsers(Button boutonDeco, Button btnRetourAdmin,TextField tfSearch,Button btnSearch,TableView<Utilisateur> table,GestionUtilisateurs gestionUtilisateurs,TableColumn<Utilisateur, Boolean> isActifCol,Button btnDeleteUser,Button btnRefresh,Button btnEdit){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnRetourAdmin = btnRetourAdmin;
        this.tfSearch=tfSearch;
        this.btnSearch=btnSearch;
        this.table=table;
        this.isActifCol=isActifCol;
        this.btnDeleteUser=btnDeleteUser;
        this.btnRefresh=btnRefresh;
        this.btnEdit=btnEdit;

        this.btnRetourAdmin.setId("btnRetourAdmin");
        this.btnDeleteUser.setId("btnDeleteUser");
        this.btnRefresh.setId("btnRefresh");
        this.btnSearch.setId("search");
        this.btnEdit.setId("btnEdit");

        this.btnRetourAdmin.setCursor(Cursor.HAND);
        this.btnDeleteUser.setCursor(Cursor.HAND);
        this.btnRefresh.setCursor(Cursor.HAND);
        this.btnSearch.setCursor(Cursor.HAND);
        this.btnEdit.setCursor(Cursor.HAND);

        this.setTop(this.enHaut());     
        this.setCenter(this.center());
    }
    
    private BorderPane enHaut(){
        BorderPane bp = new BorderPane();
        Text logo = new Text("VAE");
        logo.setId("titreVAE");
        logo.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 80));

        Text titre = new Text("Gestion des utilisateurs");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 50   ));
        titre.setId("titreModuleAdmin");

        this.btnDeconnexion.setId("btnDeconnexion");
        this.btnDeconnexion.setGraphic(new ImageView(new Image("file:./ressources/graphics/user.png")));
        this.btnDeconnexion.setCursor(Cursor.HAND);

        bp.setLeft(logo);
        bp.setCenter(titre);
        bp.setRight(this.btnDeconnexion);
        bp.setStyle("-fx-background-color:#51D1E6");
        bp.setPadding(new Insets(40,20,40,20));
        BorderPane.setAlignment(this.btnDeconnexion, Pos.CENTER);
        BorderPane.setMargin(logo, new Insets(10,30,10,10));
        BorderPane.setMargin(this.btnDeconnexion, new Insets(10,10,10,30));

        return bp;
    }

    private BorderPane center() {
        BorderPane center = new BorderPane();
        center.setPadding(new Insets(20, 20, 20, 20));
        HBox menu = new HBox(20);
        VBox boxButtons = new VBox(50);
        menu.setAlignment(Pos.CENTER);
        boxButtons.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(10, 10, 10, 10));

        tfSearch.setPromptText("Rechercher par nom d'utilisateur :");
        tfSearch.setPrefWidth(500);
        tfSearch.setPrefHeight(40);
        
    
        TableColumn<Utilisateur, Integer> idCol =
            new TableColumn<>("ID");
        idCol.setPrefWidth(80);


        TableColumn<Utilisateur, String> usernameCol =
                new TableColumn<>("Nom d'utilisateur");
        usernameCol.setPrefWidth(150);

        TableColumn<Utilisateur, String> emailCol =
                new TableColumn<>("Email");
        emailCol.setPrefWidth(250);

        // TableColumn<Utilisateur, String> passwordCol =
        //         new TableColumn<>("Mot de passe");
        // passwordCol.setPrefWidth(200);

        TableColumn<Utilisateur, Integer> roleCol =
                new TableColumn<>("Role");
        roleCol.setPrefWidth(80);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        // passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("idRole"));
    
        List<TableColumn<Utilisateur, ?>> columns = new ArrayList<>();
        columns.add(idCol);
        columns.add(usernameCol);
        columns.add(emailCol);
        // columns.add(passwordCol);
        columns.add(isActifCol);
        columns.add(roleCol);
    
        this.table.getColumns().addAll(columns);
        this.table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        BorderPane.setMargin(table, new Insets(10, 10, 10, 10));

        menu.getChildren().addAll(this.tfSearch, this.btnSearch);
        boxButtons.getChildren().addAll(this.btnRefresh, this.btnDeleteUser,this.btnEdit);
        center.setTop(menu);
        center.setCenter(this.table);
        center.setBottom(this.btnRetourAdmin);
        center.setRight(boxButtons);


        return center;
    }
    
}
