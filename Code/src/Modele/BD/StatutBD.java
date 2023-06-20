package Modele.BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modele.Statut;
public class StatutBD {
    private ConnexionMySQL laConnexion;
	private Statement st;
	public StatutBD(ConnexionMySQL laConnexion){
		this.laConnexion= laConnexion;
		try{
			this.st= this.laConnexion.createStatement();
		}catch(Exception exep){
			System.out.println(exep);
		}
		
        
	}
    public void ajouteStatut(Statut statut)throws SQLException{
       PreparedStatement ps= this.laConnexion.prepareStatement("insert into STATUT values(?,?)");
        ps.setString(1, String.valueOf(statut.getIdentifiant()));
        ps.setString(2, statut.getNom());
        ps.executeUpdate();
    }
    public List<Statut> listeStatut() throws SQLException{
        ResultSet rs=this.laConnexion.prepareStatement("select * from STATUT").executeQuery();
        List<Statut> liste= new ArrayList<>();
        while(rs.next()){
            Statut stat= new Statut(rs.getString("idst").charAt(0), rs.getString("nomst"));
            liste.add(stat);
        }
        return liste;
    }
}