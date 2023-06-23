package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Role;
import Modele.Utilisateur;


public class UtilisateurBd {
    private Connection laConnexion;
    public UtilisateurBd(Connection laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.laConnexion.createStatement();
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
        ps.setInt(6, util.getRole().getIdRole());
        
        
        ps.executeUpdate();
    }

    public List<Utilisateur> getUtilisateurs() throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from UTILISATEUR").executeQuery();
        List<Utilisateur> liste=new ArrayList<>();
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
            Role rle=null;
            for (Role role : listeRoles){
                if (role.getIdRole()==rs.getInt("idrole")){
                    rle=role;
                }
            }
            Utilisateur util= new Utilisateur(id, pseudo, email, mdp, act, rle);

            
            liste.add(util);
        }
        return liste;
    }
}
