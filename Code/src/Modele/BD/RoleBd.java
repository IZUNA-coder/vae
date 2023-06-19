import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import Modele.BD.ConnexionMySQL;

public class RoleBd {
    private ConnexionMySQL laConnexion;
    private Statement st;

    public RoleBd(ConnexionMySQL laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.st=this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereRole(Role role) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into ROLE  values (?,?)");
        ps.setInt(1, role.getIdRole());
        ps.setString(2, role.getNomRole());
        
        ps.executeUpdate();
    }

    public List<Role> getRoles() throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from ROLE").executeQuery();
        List<Role> liste=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("idrole");
            String nom=rs.getString("nomrole");
            Role role= new Role(id, nom);
            liste.add(role);
        }
        return liste;
    }
}

