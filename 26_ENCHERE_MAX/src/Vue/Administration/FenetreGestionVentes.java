package Vue.Administration;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

import Modele.Vente;
import Modele.BD.GestionVentes;
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
    private Button btnDeleteVente;
    private Button btnAddVente;
    
    public FenetreGestionVentes(Button boutonDeco, Button btnRetourAdmin,TextField tfSearch,Button btnSearch,TableView<Vente> table,GestionVentes gestionVentes,Button btnDeleteVente,Button btnRefresh,Button btnEdit,Button btnAddVente){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnRetourAdmin = btnRetourAdmin;
        this.tfSearch=tfSearch;
        this.btnAddVente=btnAddVente;
        this.btnSearch=btnSearch;
        this.table=table;
        this.btnDeleteVente=btnDeleteVente;
        this.btnRefresh=btnRefresh;
        this.btnEdit=btnEdit;

        this.btnRetourAdmin.setId("btnRetourAdmin");
        this.btnDeleteVente.setId("btnDeleteUser");
        this.btnRefresh.setId("btnRefresh");
        this.btnSearch.setId("search");
        this.btnEdit.setId("btnEdit");
        this.btnAddVente.setId("btnEdit");

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
        idCol.setPrefWidth(100);

        TableColumn<Vente, Double> prixBaseCol =
            new TableColumn<>("Prix de base");
        prixBaseCol.setPrefWidth(100);

        TableColumn<Vente, Double> prixMinCol =
            new TableColumn<>("Prix minimum");
        prixMinCol.setPrefWidth(100);

        TableColumn<Vente, Date> debutVeCol =
            new TableColumn<>("Début de la vente");
        debutVeCol.setPrefWidth(150);

        TableColumn<Vente, Date> finVeCol =
            new TableColumn<>("Fin de la vente");
        finVeCol.setPrefWidth(150);

        TableColumn<Vente, Integer> idStatutCol =
                new TableColumn<>("Statut");
        idStatutCol.setPrefWidth(150);

        TableColumn<Vente, Integer> idObjCol =
                new TableColumn<>("ID de l'objet");
        idObjCol.setPrefWidth(100);


        

        idCol.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        prixBaseCol.setCellValueFactory(new PropertyValueFactory<>("prixBase"));
        prixMinCol.setCellValueFactory(new PropertyValueFactory<>("prixMin"));
        debutVeCol.setCellValueFactory(new PropertyValueFactory<>("debutVente"));
        finVeCol.setCellValueFactory(new PropertyValueFactory<>("finVente"));
        idStatutCol.setCellValueFactory(new PropertyValueFactory<>("nomStatut"));
        idObjCol.setCellValueFactory(new PropertyValueFactory<>("idObjet"));
    
        List<TableColumn<Vente, ?>> columns = new ArrayList<>();
        columns.add(idCol);
        columns.add(prixBaseCol);
        columns.add(prixMinCol);
        columns.add(debutVeCol);
        columns.add(finVeCol);
        columns.add(idStatutCol);
        columns.add(idObjCol);
    
        this.table.getColumns().addAll(columns);
        this.table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        BorderPane.setMargin(table, new Insets(10, 10, 10, 10));

        menu.getChildren().addAll(this.tfSearch, this.btnSearch);
        boxButtons.getChildren().addAll(this.btnRefresh, this.btnDeleteVente,this.btnEdit, this.btnAddVente);
        center.setTop(menu);
        center.setCenter(this.table);
        center.setBottom(this.btnRetourAdmin);
        center.setRight(boxButtons);


        return center;
    }
    
}
