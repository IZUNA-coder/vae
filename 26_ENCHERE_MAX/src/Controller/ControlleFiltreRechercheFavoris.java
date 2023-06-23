package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

import java.sql.SQLException;
import java.util.List;

import Modele.Categorie;
import Modele.Vente;
import Modele.BD.GestionVentes;
import Vue.AppliVAE;
import Vue.FenetreRechercheEnchere;
import javafx.event.ActionEvent ;


public class ControlleFiltreRechercheFavoris implements EventHandler<ActionEvent>{
    
    private FenetreRechercheEnchere vue;
    private GestionVentes gestionVentes;
    private AppliVAE appli;
    
    public ControlleFiltreRechercheFavoris(FenetreRechercheEnchere vue,GestionVentes gestionVentes,AppliVAE appli){
        this.vue = vue;
        this.gestionVentes = gestionVentes;
        this.appli = appli;

    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Filres cliqué");
        CheckBox cb = (CheckBox) event.getSource();
        try{
            if(cb.getText().equals("Favoris")){
                if (cb.isSelected()) {
                    // Le bouton favori est sélectionné
                    this.vue.mettreAJourResultats(gestionVentes.getVentesFavorites(this.appli.getUtilisateur().getId()),"Favoris");
                } else {
                    // Le bouton favori n'est pas sélectionné
                    List<Vente> listeVente = null;
                    listeVente = gestionVentes.getVenteRecherche(this.vue.getTextNavBar());
                    this.vue.mettreAJourResultats(listeVente,this.vue.getTextNavBar());
                }
            } else{
                for(int i=0 ;i<Categorie.nomCategorie.size();i++){
                    String nomCat = Categorie.nomCategorie.get(i);
                    Categorie cat = new Categorie(nomCat);
                    if(cb.getText().equals(nomCat)){
                        System.out.println("Categorie : " + nomCat);
                        if (cb.isSelected()) {
                            // Le bouton favori est sélectionné
                            this.vue.mettreAJourResultats(gestionVentes.getVenteParCategorie(cat.getIdentifiant()),nomCat+"");
                        } else {
                            // Le bouton favori n'est pas sélectionné
                            List<Vente> listeVente = null;
                            listeVente = gestionVentes.getVenteRecherche(this.vue.getTextNavBar());
                            this.vue.mettreAJourResultats(listeVente,this.vue.getTextNavBar());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

