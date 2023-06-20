package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

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
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into OBJET  values (?,?,?,?,?)");
        ps.setInt(1, vente.getIdentifiant());
        ps.setDouble(2, vente.getPrixBase());
        ps.setDouble(3, vente.getPrixMin());
        ps.setDate(4, vente.getDebutVente());
        ps.setDate(5, vente.getFinVente());
        ps.setInt(6, vente.getObjet().getIdentifiant());
        ps.setInt(7, vente.getStatut().getIdentifiant());

        
        
        ps.executeUpdate();
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
}
