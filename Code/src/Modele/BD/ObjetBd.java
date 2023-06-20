package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Objet;

public class ObjetBd {
    private Connection laConnexion;
    public ObjetBd(Connection laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.laConnexion.createStatement();
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
        PreparedStatement ps=this.laConnexion.prepareStatement("select * from OBJET");
        ResultSet rs= ps.executeQuery();
        List<Objet> liste=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("idob");
            String nom=rs.getString("nomob");
            String description=rs.getString("descriptionob");
            int idut=rs.getInt("idut");
            int idcat=rs.getInt("idcat");
            
            // UtilisateurBd utilBd=new UtilisateurBd(laConnexion);
            // List<Utilisateur> listeUtil = utilBd.getUtilisateurs();
            // Utilisateur utili=null;
            // for (Utilisateur util : listeUtil){
            //     if (util.getId()==idut){
            //         System.out.println(idut);
            //         utili=util;
            //     }
            // }

            // CategorieBD catBd=new CategorieBD(laConnexion);
            // List<Categorie> listeCategories = catBd.listeCategories();
            // Categorie cate=null;
            // for (Categorie cat : listeCategories){
            //     if (cat.getIdentifiant()==idcat){
            //         cate=cat;
            //     }
            // }

            Objet obj= new Objet(id, nom, description, idut, idcat);
            liste.add(obj);
        }
        return liste;
    }

    public Objet getObjet(int idObj){
        try{
            PreparedStatement ps=this.laConnexion.prepareStatement("select * from OBJET where idob=?");
            ps.setInt(1, idObj);
            ResultSet rs= ps.executeQuery();
            rs.next();
            int id=rs.getInt("idob");
            String nom=rs.getString("nomob");
            String description=rs.getString("descriptionob");
            int idut=rs.getInt("idut");
            int idcat=rs.getInt("idcat");
            
            Objet obj= new Objet(id, nom, description, idut, idcat);
            return obj;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
