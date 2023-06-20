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
 
public class FenetreGestionEntreprise extends BorderPane {
    
    private Button btnDeconnexion;
    private Button btnRetourAdmin;
    private Label lbNbTotUsers;
    private Label lbNbUsersActif;
    private Label lbNbUsersInactif;
    
    public FenetreGestionEntreprise(Button boutonDeco, Button btnRetourAdmin,Label lbNbTotUsers,Label lbNbUsersActif,Label lbNbUsersInactif){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnRetourAdmin = btnRetourAdmin;
        this.lbNbTotUsers=lbNbTotUsers;
        this.lbNbUsersActif=lbNbUsersActif;
        this.lbNbUsersInactif=lbNbUsersInactif;

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
        VBox nbTotUsers = new VBox();
        VBox nbUsersActif = new VBox();
        VBox nbUsersInactif = new VBox();

        nbTotUsers.setPadding(new Insets(10, 10, 10, 10));
        nbUsersActif.setPadding(new Insets(10, 10, 10, 10));
        nbUsersInactif.setPadding(new Insets(10, 10, 10, 10));
        nbTotUsers.setAlignment(Pos.CENTER);
        nbUsersActif.setAlignment(Pos.CENTER);
        nbUsersInactif.setAlignment(Pos.CENTER);

        nbTotUsers.setId("boxTotUsers");
        nbUsersActif.setId("boxUsersActif");
        nbUsersInactif.setId("boxUsersInactif");

        ImageView imgTotUsers = new ImageView(new Image("file:./ressources/img/totUsers.png"));
        ImageView imgUsersActif = new ImageView(new Image("file:./ressources/img/usersActif.png"));
        ImageView imgUsersInactif = new ImageView(new Image("file:./ressources/img/usersInactif.png"));
        imgTotUsers.setFitWidth(100);
        imgTotUsers.setFitHeight(100);
        imgUsersActif.setFitWidth(100);
        imgUsersActif.setFitHeight(100);
        imgUsersInactif.setFitWidth(100);
        imgUsersInactif.setFitHeight(100);

        Label txtTotUsers = new Label("Utilisateurs Totaux");
        Label txtUsersActif = new Label("Utilisateurs Actifs");
        Label txtUsersInactif = new Label("Utilisateurs Inactifs");

        txtTotUsers.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));
        txtUsersActif.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));
        txtUsersInactif.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));

        txtTotUsers.setId("txtDashboard");
        txtUsersActif.setId("txtDashboard");
        txtUsersInactif.setId("txtDashboard");
        this.lbNbTotUsers.setId("txtDashboard");
        this.lbNbUsersActif.setId("txtDashboard");
        this.lbNbUsersInactif.setId("txtDashboard");

        this.lbNbTotUsers.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 20));
        this.lbNbUsersActif.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 20));
        this.lbNbUsersInactif.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 20));

        nbTotUsers.getChildren().addAll(imgTotUsers,txtTotUsers,this.lbNbTotUsers);
        nbUsersActif.getChildren().addAll(imgUsersActif,txtUsersActif,this.lbNbUsersActif);
        nbUsersInactif.getChildren().addAll(imgUsersInactif,txtUsersInactif,this.lbNbUsersInactif);

        PieChart pieChart = new PieChart();
        pieChart.setTitle("Répartition des utilisateurs");
        pieChart.getData().setAll(
            new PieChart.Data("Actifs",Integer.parseInt(this.lbNbUsersActif.getText())),
            new PieChart.Data("Inactifs", Integer.parseInt(this.lbNbUsersInactif.getText())));

        dashboard.getChildren().addAll(nbTotUsers,nbUsersActif,nbUsersInactif);
        center.setPadding(new Insets(100,20,20,20));
        stats.getChildren().addAll(dashboard,pieChart);
        center.setCenter(stats);
        center.setBottom(this.btnRetourAdmin);
        return center;
    }
}
