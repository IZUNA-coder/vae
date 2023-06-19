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
import java.util.Date;
import java.util.List;

import Modele.GestionVentes;
import Modele.Utilisateur;
import Modele.Vente;
import Modele.BD.GestionUtilisateurs;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 
public class FenetreGestionVentes extends BorderPane {
    
    private Button btnDeconnexion;
    private Button btnRetourAdmin;
    private TextField tfSearch;
    private Button btnSearch;
    private Button btnRefresh;
    private Button btnEdit;
    private TableView<Vente> table;
    private TableColumn<Vente, Boolean> isActifCol;
    private Button btnDeleteVente;
    
    public FenetreGestionVentes(Button boutonDeco, Button btnRetourAdmin,TextField tfSearch,Button btnSearch,TableView<Vente> table,GestionVentes gestionVentes,TableColumn<Vente, Boolean> isActifCol,Button btnDeleteVente,Button btnRefresh,Button btnEdit){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnRetourAdmin = btnRetourAdmin;
        this.tfSearch=tfSearch;
        this.btnSearch=btnSearch;
        this.table=table;
        this.isActifCol=isActifCol;
        this.btnDeleteVente=btnDeleteVente;
        this.btnRefresh=btnRefresh;
        this.btnEdit=btnEdit;

        this.btnRetourAdmin.setId("btnRetourAdmin");
        this.btnDeleteVente.setId("btnDeleteUser");
        this.btnRefresh.setId("btnRefresh");
        this.btnSearch.setId("search");
        this.btnEdit.setId("btnEdit");

        this.btnRetourAdmin.setCursor(Cursor.HAND);
        this.btnDeleteVente.setCursor(Cursor.HAND);
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

        Text titre = new Text("Gestion des ventes");
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

        tfSearch.setPromptText("Rechercher par nom d'objet :");
        tfSearch.setPrefWidth(500);
        tfSearch.setPrefHeight(40);
        
    
        TableColumn<Vente, Integer> idCol =
            new TableColumn<>("ID");
        idCol.setPrefWidth(80);

        TableColumn<Vente, Integer> prixBaseCol =
            new TableColumn<>("Prix de base");
        prixBaseCol.setPrefWidth(80);

        TableColumn<Vente, Integer> prixMinCol =
            new TableColumn<>("Prix minimum");
        prixMinCol.setPrefWidth(80);

        TableColumn<Vente, Date> debutVeCol =
            new TableColumn<>("Début de la vente");
        debutVeCol.setPrefWidth(80);

        TableColumn<Vente, Date> finVeCol =
            new TableColumn<>("Fin de la vente");
        finVeCol.setPrefWidth(80);

        TableColumn<Vente, String> idObjCol =
                new TableColumn<>("ID de l'objet");
        idObjCol.setPrefWidth(150);

        TableColumn<Vente, String> idStatutCol =
                new TableColumn<>("Statut");
        idStatutCol.setPrefWidth(250);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixBaseCol.setCellValueFactory(new PropertyValueFactory<>("prixbase"));
        prixMinCol.setCellValueFactory(new PropertyValueFactory<>("prixmin"));
        debutVeCol.setCellValueFactory(new PropertyValueFactory<>("debutve"));
        finVeCol.setCellValueFactory(new PropertyValueFactory<>("finve"));
        idObjCol.setCellValueFactory(new PropertyValueFactory<>("idob"));
        idStatutCol.setCellValueFactory(new PropertyValueFactory<>("idst"));
    
        List<TableColumn<Vente, ?>> columns = new ArrayList<>();
        columns.add(idCol);
        columns.add(prixBaseCol);
        columns.add(prixMinCol);
        columns.add(finVeCol);
        columns.add(finVeCol);
        columns.add(idObjCol);
        columns.add(idStatutCol);
    
        this.table.getColumns().addAll(columns);
        this.table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        BorderPane.setMargin(table, new Insets(10, 10, 10, 10));

        menu.getChildren().addAll(this.tfSearch, this.btnSearch);
        boxButtons.getChildren().addAll(this.btnRefresh, this.btnDeleteVente,this.btnEdit);
        center.setTop(menu);
        center.setCenter(this.table);
        center.setBottom(this.btnRetourAdmin);
        center.setRight(boxButtons);


        return center;
    }
    
}
