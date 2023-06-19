import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Enchere;

public class EnchereBd {
    private ConnexionMySQL laConnexion;
    private Statement st;

    public EnchereBd(ConnexionMySQL laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.st=this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereEnchere(Enchere enchere) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into ENCHERIR  values (?,?,?,?)");
        ps.setInt(1, enchere.getUtilisateur().getId());
        ps.setInt(2, enchere.getVente().getIdentifiant());
        ps.setDate(3, enchere.getDateHeure());
        ps.setLong(4, enchere.getPrix());
        ps.executeUpdate();
    }

    public List<Enchere> getEncheres() throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from ENCHERIR natural join s")
    }
}
