package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Objet;

public class ObjetBd {
    private Connection laConnexion;
    /**
     * Constructeur de la classe ObjetBd
     * @param laConnexion
     */
    public ObjetBd(Connection laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    /**
     * Insère un objet dans la base de données
     * @param objet
     * @throws SQLException
     */
    public void insereObjet(Objet objet) throws SQLException{
        // récuperer id max de la table vente
        PreparedStatement ps2=this.laConnexion.prepareStatement("select max(idob) from OBJET");
        ResultSet rs= ps2.executeQuery();
        rs.next();
        int id=rs.getInt(1)+1;

        PreparedStatement ps=this.laConnexion.prepareStatement("insert into OBJET  values (?,?,?,?,?)");
        ps.setInt(1, id);
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

            Objet obj= new Objet(id, nom, description, idut, idcat);
            liste.add(obj);
        }
        return liste;
    }
    /**
     * Retourne le dernier id de la table objet
     * @return int
     */
    public int dernierId(){

        try{
            PreparedStatement ps=this.laConnexion.prepareStatement("select max(idob) as maxid from OBJET");
            ResultSet rs= ps.executeQuery();
            rs.next();
            int id=rs.getInt("maxid");
            return id;
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }

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
