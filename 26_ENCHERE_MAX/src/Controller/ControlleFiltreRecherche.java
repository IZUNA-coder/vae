package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

import java.sql.SQLException;

import Modele.Categorie;
import Modele.BD.GestionVentes;
import Vue.AppliVAE;
import Vue.FenetreRechercheEnchere;
import javafx.event.ActionEvent ;


public class ControlleFiltreRecherche implements EventHandler<ActionEvent>{
    
    private FenetreRechercheEnchere vue;
    private GestionVentes gestionVentes;
    private AppliVAE appli;
    
    public ControlleFiltreRecherche(FenetreRechercheEnchere vue,GestionVentes gestionVentes,AppliVAE appli){
        this.vue = vue;
        this.gestionVentes = gestionVentes;
        this.appli = appli;

    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Filres cliqué");
        RadioButton cb = (RadioButton) event.getSource();
        System.out.println("Filtre : " + cb.getText());
        try{
            if(cb.getText().equals("Favoris")){
                this.vue.mettreAJourResultats(gestionVentes.getVentesFavorites(this.appli.getUtilisateur().getId()),"Favoris");
            } else if(cb.getText().equals("Ventes Encheries")){
                System.out.println("Ventes Encheries");
                this.vue.mettreAJourResultats(gestionVentes.getVenteEncheriesUtilisateur(this.appli.getUtilisateur().getId()),"Ventes Encheries");
            }else if(cb.getText().equals("Toutes les ventes")){
                System.out.println("Toutes les ventes");
                this.vue.mettreAJourResultats(gestionVentes.getVente(),"Toutes les ventes");
            } else if(cb.getText().equals("Toutes les catégories")){
                System.out.println("Toutes les catégories");
                this.vue.mettreAJourResultats(gestionVentes.getVente(),"Toutes les catégories");
                
            }
            else{
                for(int i=0 ;i<Categorie.nomCategorie.size();i++){
                    String nomCat = Categorie.nomCategorie.get(i);
                    Categorie cat = new Categorie(nomCat);
                    if(cb.getText().equals(nomCat)){
                        System.out.println("Categorie : " + nomCat);
                        this.vue.mettreAJourResultats(gestionVentes.getVenteParCategorie(cat.getIdentifiant()),nomCat+"");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

