package Controller;

import java.sql.SQLException;
import java.util.List;

import Modele.Vente;
import Modele.BD.GestionVentes;
import Vue.FenetreRechercheEnchere;

public class ControllerRechercheTempsReel {
    private FenetreRechercheEnchere vue;
    private GestionVentes gestionVentes;

    public ControllerRechercheTempsReel(FenetreRechercheEnchere vue,GestionVentes gestionVentes) {
        this.vue = vue;
        this.gestionVentes = gestionVentes;
    }

    public void rechercher(String recherche) {
        // Mettez ici votre logique de recherche en fonction de la nouvelle valeur
        // de recherche et mettez à jour les résultats dans la vue.
        // Par exemple, vous pouvez appeler une méthode pour mettre à jour les résultats dans la vue :
        this.vue.setSelectedBtnFavoris(false);

        List<Vente> listeVente = null;
        try {
            listeVente = gestionVentes.getVenteRecherche(recherche);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        vue.mettreAJourResultats(listeVente,recherche);
    }

}
