package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Categorie;
import Modele.Objet;
import Modele.Vente;


public class GestionVentes {
    private Connection laConnexion;
    public GestionVentes(Connection laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereVente(Vente vente) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into OBJET  values (?,?,?,?,?,?,?)");
        ps.setInt(1, vente.getIdentifiant());
        ps.setDouble(2, vente.getPrixBase());
        ps.setDouble(3, vente.getPrixMin());
        ps.setDate(4, vente.getDebutVente());
        ps.setDate(5, vente.getFinVente());
        ps.setInt(6, vente.getObjet().getIdentifiant());
        ps.setInt(7, vente.getStatut().getIdentifiant());

        
        
        ps.executeUpdate();
    }

    public List<Vente> getVentesAleatoires() {
        List<Vente> liste = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i) {
                int nbAleatoire = (int) (Math.random() * this.getVente().size());
                liste.add(this.getVente().get(nbAleatoire));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes aléatoires");
        }
        return liste;
    }

    public int getNbVentes(){
        int nb=0;
        try{
            PreparedStatement ps=this.laConnexion.prepareStatement("select count(*) from VENTE");
            ResultSet rs= ps.executeQuery();
            rs.next();
            nb=rs.getInt(1);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return nb;
    }

    public List<Vente> getVente() throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("select * from VENTE");
        ResultSet rs= ps.executeQuery();
        List<Vente> liste=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("idve");
            double prixBase=rs.getDouble("prixbase");
            double prixMin=rs.getDouble("prixmin");
            Date debut=rs.getDate("debutve");
            Date fin=rs.getDate("finve");
            int stat=rs.getInt("idst");

            ObjetBd objBd=new ObjetBd(laConnexion);
            Objet obj = objBd.getObjet(rs.getInt("idob"));

            Vente vente= new Vente(id, prixBase, prixMin, debut, fin, stat, obj);
            liste.add(vente);
        }
        return liste;
    }

    public List<Vente> getVenteRecherche(String nomObj) throws SQLException{
        ArrayList<Vente> liste = new ArrayList<>();
        String query = "SELECT * from VENTE NATURAL JOIN OBJET WHERE nomob LIKE ? limit 15";
        try (PreparedStatement statement = this.laConnexion.prepareStatement(query)) {
            statement.setString(1, "%" + nomObj + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("idve");
                double prixBase=rs.getDouble("prixbase");
                double prixMin=rs.getDouble("prixmin");
                Date debut=rs.getDate("debutve");
                Date fin=rs.getDate("finve");
                int stat=rs.getInt("idst");

                ObjetBd objBd=new ObjetBd(laConnexion);
                Objet obj = objBd.getObjet(rs.getInt("idob"));

                Vente vente= new Vente(id, prixBase, prixMin, debut, fin, stat, obj);
                liste.add(vente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return liste;
    }

    public void ajouterVenteFavorite(int idUtilisateur, int idVente) throws SQLException {
    // Vérifier si l'utilisateur existe
    PreparedStatement checkUser = this.laConnexion.prepareStatement("SELECT * FROM UTILISATEUR WHERE idut = ?");
    checkUser.setInt(1, idUtilisateur);
    ResultSet userResult = checkUser.executeQuery();
    if (!userResult.next()) {
        System.out.println("L'utilisateur avec l'ID " + idUtilisateur + " n'existe pas.");
        return;
    }

    // Ajouter la vente favorite
    PreparedStatement insertFavoris = this.laConnexion.prepareStatement("INSERT INTO FAVORIS (idut, idve) VALUES (?, ?)");
    insertFavoris.setInt(1, idUtilisateur);
    insertFavoris.setInt(2, idVente);
    insertFavoris.executeUpdate();
}

    
    public void supprimerVenteFavorite(int idUtilisateur, int idVente) throws SQLException {
        PreparedStatement ps = this.laConnexion.prepareStatement("DELETE FROM FAVORIS WHERE idut = ? AND idve = ?");
        ps.setInt(1, idUtilisateur);
        ps.setInt(2, idVente);
        ps.executeUpdate();
    }
    
    public List<Vente> getVentesFavorites(int idUtilisateur) throws SQLException {
        PreparedStatement ps = this.laConnexion.prepareStatement("SELECT * FROM VENTE JOIN FAVORIS ON VENTE.idve = FAVORIS.idve WHERE FAVORIS.idut = ?");
        ps.setInt(1, idUtilisateur);
        ResultSet rs = ps.executeQuery();
        
        List<Vente> liste = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idve");
            double prixBase = rs.getDouble("prixbase");
            double prixMin = rs.getDouble("prixmin");
            Date debut = rs.getDate("debutve");
            Date fin = rs.getDate("finve");
            int stat = rs.getInt("idst");

            ObjetBd objBd = new ObjetBd(laConnexion);
            Objet obj = objBd.getObjet(rs.getInt("idob"));

            Vente vente = new Vente(id, prixBase, prixMin, debut, fin, stat, obj);
            liste.add(vente);
        }
        
        return liste;
    }

    /* retourne true si la vente est dans les favoris de l'utilisateur, false sinon*/
    public boolean estFavoris(int idUt, int idVente) throws SQLException {
        PreparedStatement ps = this.laConnexion.prepareStatement("SELECT * FROM FAVORIS WHERE idut = ? AND idve = ?");
        ps.setInt(1, idUt);
        ps.setInt(2, idVente);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public List<Vente> getVenteParCategorie(int cat) throws SQLException {
        List<Vente> filteredList = new ArrayList<>();
        List<Vente> listeVentes = getVente();
        
        for (Vente v : listeVentes) {
            Categorie categ = v.getObjet().getCategorie();
            if (categ.getIdentifiant() == cat) {
                filteredList.add(v);
            }
        }
        
        return filteredList;
    }
    
}
