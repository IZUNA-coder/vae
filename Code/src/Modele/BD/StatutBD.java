package Modele.BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modele.Statut;
public class StatutBD {
    private Connection laConnexion;
	public StatutBD(Connection laConnexion){
		this.laConnexion= laConnexion;
		try{
			this.laConnexion.createStatement();
		}catch(Exception exep){
			System.out.println(exep);
		}
		
        
	}
    public void ajouteStatut(Statut statut)throws SQLException{
       PreparedStatement ps= this.laConnexion.prepareStatement("insert into STATUT values(?,?)");
        ps.setInt(1, statut.getIdentifiant());
        ps.setString(2, statut.getNom());
        ps.executeUpdate();
    }
    public List<Statut> listeStatut() throws SQLException{
        ResultSet rs=this.laConnexion.prepareStatement("select * from STATUT").executeQuery();
        List<Statut> liste= new ArrayList<>();
        while(rs.next()){
            Statut stat= new Statut(rs.getInt("idst"), rs.getString("nomst"));
            liste.add(stat);
        }
        return liste;
    }
}
