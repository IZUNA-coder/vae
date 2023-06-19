import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Categorie;
import Modele.Objet;
import Modele.Utilisateur;

public class ObjetBd {
    private ConnexionMySQL laConnexion;
    private Statement st;

    public ObjetBd(ConnexionMySQL laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.st=this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereObjet(Objet objet) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into OBJET  values (?,?,?,?,?)");
        ps.setInt(1, objet.getIdentifiant());
        ps.setString(2, objet.getNom());
        ps.setString(3, objet.getDescription());
        ps.setInt(4, objet.getUtilisateur().getId());
        ps.setInt(5, objet.getCategorie().getIdentifiant());

        
        
        ps.executeUpdate();
    }

    public List<Objet> getObjets() throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from OBJET").executeQuery();
        List<Objet> liste=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("idob");
            String nom=rs.getString("nomob");
            String description=rs.getString("descriptionob");
            
            UtilisateurBd utilBd=new UtilisateurBd(laConnexion);
            List<Utilisateur> listeUtil = utilBd.getUtilisateurs();
            Utilisateur utili=null;
            for (Utilisateur util : listeUtil){
                if (util.getId()==rs.getInt("idut")){
                    utili=util;
                }
            }

            CategorieBD catBd=new CategorieBD(laConnexion);
            List<Categorie> listeCategories = catBd.listeCategories();
            Categorie cate=null;
            for (Categorie cat : listeCategories){
                if (cat.getIdentifiant()==rs.getInt("idcat")){
                    cate=cat;
                }
            }
            Objet obj= new Objet(id, nom, description, cate, utili);
            liste.add(obj);
        }
        return liste;
    }
}
