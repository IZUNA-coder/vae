package Vue.Administration;

import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 
public class FenetreGestionVentes extends BorderPane {
    
    private Button btnDeconnexion;
    private Button btnRetourAdmin;
    private Label lbNbTotVentes;
    private Label lbNbVentesValidée;
    private Label lbNbVentesNonConclus;
    
    public FenetreGestionVentes(Button boutonDeco, Button btnRetourAdmin,Label lbNbTotVentes,Label lbNbVentesValidée,Label lbNbVentesNonConclus){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnRetourAdmin = btnRetourAdmin;
        this.lbNbTotVentes=lbNbTotVentes;
        this.lbNbVentesValidée=lbNbVentesValidée;
        this.lbNbVentesNonConclus=lbNbVentesNonConclus;

        this.btnRetourAdmin.setId("btnRetourAdmin");
        this.btnRetourAdmin.setCursor(Cursor.HAND);
        
        this.setTop(this.enHaut());     
        this.setCenter(this.center());
    }
    
    private BorderPane enHaut(){
        BorderPane bp = new BorderPane();
        Text logo = new Text("VAE");
        logo.setId("titreVAE");
        logo.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 80));

        Text titre = new Text("Gestion de l'entreprise");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 50   ));
        titre.setId("titreModuleAdmin");

        this.btnDeconnexion.setId("btnDeconnexion");
        this.btnDeconnexion.setCursor(Cursor.HAND);
        this.btnDeconnexion.setGraphic(new ImageView(new Image("file:./ressources/graphics/user.png")));

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
        VBox stats = new VBox(30);
        HBox dashboard = new HBox(50);
        dashboard.setAlignment(Pos.BASELINE_CENTER);
        stats.setAlignment(Pos.CENTER);
        VBox nbTotVentes = new VBox();
        VBox nbVentesValidée = new VBox();
        VBox nbVentesNonConclus = new VBox();

        nbTotVentes.setPadding(new Insets(10, 10, 10, 10));
        nbVentesValidée.setPadding(new Insets(10, 10, 10, 10));
        nbVentesNonConclus.setPadding(new Insets(10, 10, 10, 10));
        nbTotVentes.setAlignment(Pos.CENTER);
        nbVentesValidée.setAlignment(Pos.CENTER);
        nbVentesNonConclus.setAlignment(Pos.CENTER);

        nbTotVentes.setId("boxTotVentes");
        nbVentesValidée.setId("boxVentesValidee");
        nbVentesNonConclus.setId("boxVentesNonConclus");

        ImageView imgTotVentes = new ImageView(new Image("file:./ressources/img/totUsers.png"));
        ImageView imgVentesValidée = new ImageView(new Image("file:./ressources/img/usersActif.png"));
        ImageView imgVentesNonConclus = new ImageView(new Image("file:./ressources/img/usersInactif.png"));
        imgTotVentes.setFitWidth(100);
        imgTotVentes.setFitHeight(100);
        imgVentesValidée.setFitWidth(100);
        imgVentesValidée.setFitHeight(100);
        imgVentesNonConclus.setFitWidth(100);
        imgVentesNonConclus.setFitHeight(100);

        Label txtTotVentes = new Label("Ventes Totales");
        Label txtVentesValidée = new Label("Ventes Validées");
        Label txtVentesNonConclus = new Label("Ventes Non Conclues");

        txtTotVentes.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));
        txtVentesValidée.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));
        txtVentesNonConclus.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));

        txtTotVentes.setId("txtDashboard");
        txtVentesValidée.setId("txtDashboard");
        txtVentesNonConclus.setId("txtDashboard");
        this.lbNbTotVentes.setId("txtDashboard");
        this.lbNbVentesValidée.setId("txtDashboard");
        this.lbNbVentesNonConclus.setId("txtDashboard");

        this.lbNbTotVentes.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 20));
        this.lbNbVentesValidée.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 20));
        this.lbNbVentesNonConclus.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 20));

        nbTotVentes.getChildren().addAll(imgTotVentes,txtTotVentes,this.lbNbTotVentes);
        nbVentesValidée.getChildren().addAll(imgVentesValidée,txtVentesValidée,this.lbNbVentesValidée);
        nbVentesNonConclus.getChildren().addAll(imgVentesNonConclus,txtVentesNonConclus,this.lbNbVentesNonConclus);

        PieChart pieChart = new PieChart();
        pieChart.setTitle("Répartition des ventes");
        pieChart.getData().setAll(
            new PieChart.Data("Validée",Integer.parseInt(this.lbNbVentesValidée.getText())),
            new PieChart.Data("Non Conclues", Integer.parseInt(this.lbNbVentesNonConclus.getText())));

        dashboard.getChildren().addAll(nbTotVentes,nbVentesValidée,nbVentesNonConclus);
        center.setPadding(new Insets(100,20,20,20));
        stats.getChildren().addAll(dashboard,pieChart);
        center.setCenter(stats);
        center.setBottom(this.btnRetourAdmin);
        return center;
    }
}
