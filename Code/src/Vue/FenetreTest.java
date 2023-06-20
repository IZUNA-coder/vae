package Vue;

import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Modele.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 
public class FenetreTest extends BorderPane {
    
    private Button btnDeconnexion;
    private Utilisateur user;
    private Button profilVendeur;
    
    public FenetreTest(Button btnDeconnexion,Utilisateur user,Button profilVendeur){
        super();
        this.btnDeconnexion = btnDeconnexion;
        this.user=user;
        this.profilVendeur=profilVendeur;
        this.btnDeconnexion.setCursor(Cursor.HAND);
        
        this.setTop(this.enHaut());     
        this.setCenter(this.center());
        this.setRight(this.right());
    }
    
    private BorderPane enHaut(){
        BorderPane bp = new BorderPane();
        Text titre = new Text("VAE - Module Utilisateur");
        titre.setFont(Font.font("Arial",FontWeight.BOLD,32));
        this.btnDeconnexion.setGraphic(new ImageView(new Image("file:./ressources/graphics/user.png")));
        bp.setLeft(titre);
        bp.setRight(this.btnDeconnexion);
        BorderPane.setAlignment(titre, Pos.CENTER);
        BorderPane.setAlignment(this.btnDeconnexion, Pos.CENTER);
        bp.setStyle("-fx-background-color:#336699");
        bp.setPadding(new Insets(10,10,10,10));
        return bp;
    }

    private VBox center() {
        VBox vb = new VBox(20);
        String text = "Bonjour " + user.getUsername() + " , bienvenue !";
        Text txt1 = new Text(text);
        txt1.setFont(Font.font("Arial",FontWeight.BOLD,20));
        ComboBox<String> cbmBox = new ComboBox<>();
        cbmBox.getItems().addAll("Pie","Line","Bar");
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Que lisez-vous au petit déjeuner ?");
        pieChart.getData().setAll(
            new PieChart.Data("Le journal", 21),
            new PieChart.Data("Un livre", 3),
            new PieChart.Data("Le courrier", 7),
            new PieChart.Data("La boîte de céréales", 75));
        pieChart.setLegendSide(Side.LEFT);
        HBox hb = new HBox();
        hb.getChildren().addAll(this.profilVendeur);
        vb.getChildren().addAll(txt1,cbmBox,pieChart,hb);
        vb.setPadding(new Insets(5,5,10,10));
        return vb;
    }

    private TilePane right(){
        TilePane tp = new TilePane();
        ImageView img1 = new ImageView(new Image("file:./ressources/graphics/chart_1.png"));
        ImageView img2 = new ImageView(new Image("file:./ressources/graphics/chart_2.png")); 
        ImageView img3 = new ImageView(new Image("file:./ressources/graphics/chart_3.png")); 
        ImageView img4 = new ImageView(new Image("file:./ressources/graphics/chart_4.png")); 
        ImageView img5 = new ImageView(new Image("file:./ressources/graphics/chart_5.png")); 
        ImageView img6 = new ImageView(new Image("file:./ressources/graphics/chart_6.png")); 
        ImageView img7 = new ImageView(new Image("file:./ressources/graphics/chart_7.png")); 
        ImageView img8 = new ImageView(new Image("file:./ressources/graphics/chart_8.png"));
        tp.getChildren().addAll(img1,img2,img3,img4,img5,img6,img7,img8);
        tp.setStyle("-fx-background-color:#dae6f3");
        return tp;
    }
}
