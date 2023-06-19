import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import Modele.BD.ConnexionMySQL;


public class UtilisateurBd {
    private ConnexionMySQL laConnexion;
    private Statement st;

    public UtilisateurBd(ConnexionMySQL laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.st=this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereUtilisateur(Utilisateur util) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into UTILISATEUR  values (?,?,?,?,?,?)");
        ps.setInt(1, util.getId());
        ps.setString(2, util.getUsername());
        ps.setString(3, util.getEmail());
        ps.setString(4, util.getPassword());
        if(util.getActif()){
            ps.setString(5, "O");
        }else{
            ps.setString(5, "N");
        }
        ps.setInt(6, util.getIdRole());
        
        
        ps.executeUpdate();
    }

    public List<Role> getUtilisateurs() throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from UTILISATEUR").executeQuery();
        List<Role> liste=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("idut");
            String pseudo=rs.getString("pseudout");
            String email=rs.getString("emailut");
            String mdp=rs.getString("mdput");
            boolean act=true;
            if(rs.getString("activeut").equals("O")){
                act=true;
            }else{
                act=false;
            }
            RoleBd rlbd=new RoleBd(laConnexion);
            List<Role> listeRoles = rlbd.getRoles();
            int idRole=0;
            for (Role role : listeRoles){
                if (role.getIdRole()==rs.getInt("idrole")){
                    idRole=rs.getInt("idrole");
                }
            }
            Utilisateur util= new Utilisateur(id, pseudo, email, mdp, act, idRole);

            
            liste.add(util);
        }
        return liste;
    }
}
