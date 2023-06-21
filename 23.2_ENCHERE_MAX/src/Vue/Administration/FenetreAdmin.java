package Vue.Administration;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 
public class FenetreAdmin extends BorderPane {
    
    private Button btnDeconnexion;
    private Button btnGestionUsers;
    private Button btnGestionSignalements;
    private Button btnGestionVentes;
    private Button btnGestioContrats;
    private Button btnGestionEntreprise;
    private Button btnGestionParamètres;
    
    public FenetreAdmin(Button boutonDeco, Button btnGestionUsers,Button btnGestionSignalements,Button btnGestionVentes,Button btnGestioContrats,Button btnGestionEntreprise,Button btnGestionParamètres){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnGestionUsers = btnGestionUsers;
        this.btnGestionSignalements = btnGestionSignalements;
        this.btnGestionVentes = btnGestionVentes;
        this.btnGestioContrats = btnGestioContrats;
        this.btnGestionEntreprise = btnGestionEntreprise;
        this.btnGestionParamètres = btnGestionParamètres;
        
        this.setTop(this.enHaut());     
        this.setCenter(this.center());
    }
    
    private BorderPane enHaut(){
        BorderPane bp = new BorderPane();
        Text logo = new Text("VAE");
        logo.setId("titreVAE");
        logo.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 80));
        Text titre = new Text("Module Administrateur");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 50   ));
        titre.setId("titreModuleAdmin");
        this.btnDeconnexion.setId("btnDeconnexion");
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

    private GridPane center() {
        GridPane gridPane = new GridPane();
        gridPane.setId("fenetreAdmin");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(50);

        ImageView imgUsers = new ImageView(new Image("file:./ressources/img/users.png"));
        ImageView imgWarning =  new ImageView(new Image("file:./ressources/img/warning.png"));
        ImageView imgSells =  new ImageView(new Image("file:./ressources/img/sells.png"));
        ImageView imgContract =  new ImageView(new Image("file:./ressources/img/contract.png"));
        ImageView imgCompany =  new ImageView(new Image("file:./ressources/img/company.png"));
        ImageView imgSettings =  new ImageView(new Image("file:./ressources/img/settings.png"));
        imgUsers.setFitWidth(50);
        imgUsers.setFitHeight(50);
        imgWarning.setFitWidth(50);
        imgWarning.setFitHeight(50);
        imgSells.setFitWidth(50);
        imgSells.setFitHeight(50);
        imgContract.setFitWidth(50);
        imgContract.setFitHeight(50);
        imgCompany.setFitWidth(50);
        imgCompany.setFitHeight(50);
        imgSettings.setFitWidth(50);
        imgSettings.setFitHeight(50);


        this.btnGestionUsers.setGraphic(imgUsers);
        this.btnGestionSignalements.setGraphic(imgWarning);
        this.btnGestionVentes.setGraphic(imgSells);
        this.btnGestioContrats.setGraphic(imgContract);
        this.btnGestionEntreprise.setGraphic(imgCompany);
        this.btnGestionParamètres.setGraphic(imgSettings);

        this.btnGestionUsers.setCursor(Cursor.HAND);
        this.btnGestionSignalements.setCursor(Cursor.HAND);
        this.btnGestionVentes.setCursor(Cursor.HAND);
        this.btnGestioContrats.setCursor(Cursor.HAND);
        this.btnGestionEntreprise.setCursor(Cursor.HAND);
        this.btnGestionParamètres.setCursor(Cursor.HAND);
        this.btnDeconnexion.setCursor(Cursor.HAND);
        
        TilePane tilePane1 = new TilePane();
        tilePane1.setAlignment(Pos.CENTER);
        tilePane1.setHgap(50);
        tilePane1.setVgap(50);

        tilePane1.getChildren().addAll(this.btnGestionUsers,this.btnGestionVentes, this.btnGestionEntreprise);
        
        TilePane tilePane2 = new TilePane();
        tilePane2.setAlignment(Pos.CENTER);
        tilePane2.setHgap(50);
        tilePane2.setVgap(50);
        tilePane2.getChildren().addAll(this.btnGestionSignalements,this.btnGestioContrats, this.btnGestionParamètres);
    
        gridPane.addRow(0, tilePane1);
        gridPane.addRow(1, tilePane2);
        
        gridPane.setStyle("-fx-background-color: #dae6f3");

        return gridPane;
    }
}
