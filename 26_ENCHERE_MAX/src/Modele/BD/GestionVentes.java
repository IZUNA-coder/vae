package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Categorie;
import Modele.Objet;
import Modele.Utilisateur;
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
    /**
     * Méthode qui permet d'insérer une vente dans la base de données a partir d'une vente
     * @param vente
     * @throws SQLException
     */
    public void insereVente(Vente vente) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into VENTE values (?,?,?,?,?,?,?)");
        // récuperer id max de la table vente
        PreparedStatement ps2=this.laConnexion.prepareStatement("select max(idve) from VENTE");
        ResultSet rs= ps2.executeQuery();
        rs.next();
        int id=rs.getInt(1)+1;

        ps.setInt(1, id);
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
            List<Vente> listeVentes = this.getVenteEnCours();
            for (int i = 0; i < 6; ++i) {
                if(listeVentes.size() == 0) {
                    return liste;
                }
                int nbAleatoire = (int) (Math.random() * listeVentes.size());
                //vérifier si vente déjà dans la liste
                if(!liste.contains(listeVentes.get(nbAleatoire))) {
                    liste.add(listeVentes.get(nbAleatoire));
                } else {
                    --i;
                }
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

    public List<Vente> getVenteEnCours() throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("select * from VENTE natural join STATUT where finve > CURRENT_TIMESTAMP and nomst='En cours'");
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

    public void supprimerVenteFavorite(int idVente) throws SQLException {
        PreparedStatement ps = this.laConnexion.prepareStatement("DELETE FROM FAVORIS WHERE idve = ?");
        ps.setInt(1, idVente);
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

    /**
     * Vérifie si une vente est dans les favoris d'un utilisateur
     */
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

    public Utilisateur getVendeur(int idve){
        Utilisateur vendeur=null;
        try{
            // jointure VENTE et UTILISATEUR pour récuperer l'id de l'utilisateur qui a créé la vente
            PreparedStatement ps=this.laConnexion.prepareStatement("SELECT U.* FROM UTILISATEUR U JOIN OBJET O ON U.idut = O.idut JOIN VENTE V ON O.idob = V.idob WHERE V.idve = ?;");
            ps.setInt(1, idve);
            ResultSet rs= ps.executeQuery();
            rs.next();
            vendeur=new Utilisateur(rs.getInt("idut"),
                rs.getString("pseudout"),
                rs.getString("emailut"),
                rs.getString("mdput"),
                rs.getBoolean("activeut"),
                rs.getInt("idrole"));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return vendeur;
    }

    public List<Vente> getVenteParUtilisateur(int idut) throws SQLException {
        List<Vente> filteredList = new ArrayList<>();
        List<Vente> listeVentes = getVente();
        
        for (Vente v : listeVentes) {
            Utilisateur vendeur = getVendeur(v.getIdentifiant());
            if (vendeur.getId() == idut) {
                filteredList.add(v);
            }
        }
        return filteredList;
    }

    public List<Vente> getVenteEncheriesUtilisateur(int idut){
        List<Vente> filteredList = new ArrayList<>();
        try{
            PreparedStatement ps=this.laConnexion.prepareStatement("select * from VENTE natural join ENCHERIR where idut=?");
            ps.setInt(1, idut);
            ResultSet rs= ps.executeQuery();
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
                filteredList.add(vente);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return filteredList;
    }
    
    public int getNbVenteConclues(int idut){
        int nbVente=0;
        try{
            PreparedStatement ps=this.laConnexion.prepareStatement("select count(*) as nbVente from VENTE where idut=? and idst=4");
            ps.setInt(1, idut);
            ResultSet rs= ps.executeQuery();
            rs.next();
            nbVente=rs.getInt("nbVente");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return nbVente;
    }

    public void supprimerVente(int idVente) throws SQLException {
        //supprimer les enchères liées à la vente
        supprimerVenteFavorite(idVente);

        supprimerEnchereVente(idVente);

        PreparedStatement ps1 = this.laConnexion.prepareStatement("DELETE FROM VENTE WHERE idve = ?");
        ps1.setInt(1, idVente);
        ps1.executeUpdate();
    }
    /**
     * Regarde si un id est existant 
     * @param idVe
     * @return
     */
    public boolean checkExistingID(int idVe) {
        // Vérifier si l'identifiant existe déjà dans la base de données
        // Utilisez votre logique de requête SQL pour effectuer la vérification
        // Retournez true si l'identifiant existe déjà, sinon retournez false
        System.out.println("Check existing ID");

        if (this.laConnexion == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }
    
        String query = "SELECT COUNT(*) FROM VENTE WHERE idve = ?";
    
        try (PreparedStatement statement = laConnexion.prepareStatement(query)) {
            statement.setInt(1, idVe);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
    
        return false;
    }
    /**
     * Permet de supprimer les encheres liées à une vente
     * @param idVente
     * @throws SQLException
     */
    public void supprimerEnchereVente(int idVente) throws SQLException {
        PreparedStatement ps = this.laConnexion.prepareStatement("DELETE FROM ENCHERIR WHERE idve = ?");
        ps.setInt(1, idVente);
        ps.executeUpdate();
    }
    /**
     * Permet de modifier l'id d'une vente
     * @param idVe
     * @param newId
     */
    public void editId(int idVe,int newId){
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            supprimerEnchereVente(idVe);
            supprimerVenteFavorite(idVe);
            String updateQuery = "UPDATE VENTE SET idve = ? WHERE idve = ?";
            PreparedStatement statement = laConnexion.prepareStatement(updateQuery);
            statement.setInt(1, newId);
            statement.setInt(2, idVe);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }
    /**
     * Permet de modifier le prix de base d'une vente
     * @param idVe
     * @param prixBase
     */
    public void editPrixBase(int idVe, Double prixBase) {
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            supprimerEnchereVente(idVe);
            supprimerVenteFavorite(idVe);
            String updateQuery = "UPDATE VENTE SET prixbase = ? WHERE idve = ?";
            PreparedStatement statement = laConnexion.prepareStatement(updateQuery);
            statement.setDouble(1, prixBase);
            statement.setInt(2, idVe);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }
    /**
     * Permet de modifier le prix minimum d'une vente
     * @param idVe
     * @param prixMin
     */
    public void editPrixMin(int idVe, Double prixMin) {
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            supprimerEnchereVente(idVe);
            supprimerVenteFavorite(idVe);
            String updateQuery = "UPDATE VENTE SET prixmin = ? WHERE idve = ?";
            PreparedStatement statement = laConnexion.prepareStatement(updateQuery);
            statement.setDouble(1, prixMin);
            statement.setInt(2, idVe);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }
    /**
     * Permet de modifier le status d'une vente
     * @param idVe
     * @param idStatut
     */
    public void editIdStatut(int idVe, int idStatut) {
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            supprimerEnchereVente(idVe);
            supprimerVenteFavorite(idVe);
            String updateQuery = "UPDATE VENTE SET idst = ? WHERE idve = ?";
            PreparedStatement statement = laConnexion.prepareStatement(updateQuery);
            statement.setInt(1, idStatut);
            statement.setInt(2, idVe);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }
    /**
     * Permet de modifier l'id d'un objet
     * @param idVe
     * @param idObj
     */
    public void editIdObj(int idVe, int idObj) {
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            supprimerEnchereVente(idVe);
            supprimerVenteFavorite(idVe);
            String updateQuery = "UPDATE VENTE SET idob = ? WHERE idve = ?";
            PreparedStatement statement = laConnexion.prepareStatement(updateQuery);
            statement.setInt(1, idObj);
            statement.setInt(2, idVe);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }

    }
    /**
     * Permet de modifier la nom de l'objet
     * @param idVe
     * @param nomObj
     */
    public void editNomObj(int idVe, String nomObj) {
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            supprimerEnchereVente(idVe);
            supprimerVenteFavorite(idVe);
            String updateQuery = "UPDATE OBJET SET nomob = ? WHERE idob = (SELECT idob FROM VENTE WHERE idve = ?)";
            PreparedStatement statement = laConnexion.prepareStatement(updateQuery);
            statement.setString(1, nomObj);
            statement.setInt(2, idVe);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }
}
