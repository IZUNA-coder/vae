package Modele.BD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modele.Categorie;
public class CategorieBD {
    private ConnexionMySQL laConnexion;
	private Statement st;
	public CategorieBD(ConnexionMySQL laConnexion){
		this.laConnexion= laConnexion;
		try{
			this.st= this.laConnexion.createStatement();
		}catch(Exception exep){
			System.out.println(exep);
		}
		
        
	}
    public void insereCategorie(Categorie cat)throws SQLException {
            PreparedStatement ps= this.laConnexion.prepareStatement("insert into CATEGORIE values (?,?)");
            ps.setInt(1, cat.getIdentifiant());
            ps.setString(2, cat.getNom());
            ps.executeQuery();
    }

    public List<Categorie> listeCategories()throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from CATEGORIE").executeQuery();
        List<Categorie> liste= new ArrayList<>();
        while(rs.next()){
            Categorie cat= new Categorie(rs.getInt("idcat"), rs.getString("nomcat"));
            liste.add(cat);
        }
        return liste;
    }
}
