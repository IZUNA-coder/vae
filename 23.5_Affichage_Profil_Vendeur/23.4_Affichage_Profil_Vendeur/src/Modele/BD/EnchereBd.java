package Modele.BD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Enchere;
import Modele.Utilisateur;
import Modele.Vente;

public class EnchereBd {
    private Connection laConnexion;
    public EnchereBd(Connection laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereEnchere(Enchere enchere) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into ENCHERIR  values (?,?,?,?)");
        ps.setInt(1, enchere.getUtilisateur().getId());
        ps.setInt(2, enchere.getVente().getIdentifiant());
        ps.setDate(3, new Date(System.currentTimeMillis()));
        ps.setDouble(4, enchere.getPrix());
        ps.executeUpdate();
    }

    public List<Enchere> getEncheres() throws SQLException{
        PreparedStatement statement = this.laConnexion.prepareStatement("select * FROM ENCHERIR");
        ResultSet rs= statement.executeQuery();
        List<Enchere> liste = new ArrayList<>();
        while(rs.next()){
            Date dateHeure=rs.getDate("dateheure");
            double prix=rs.getLong("montant");


            UtilisateurBd utilBd=new UtilisateurBd(laConnexion);
            List<Utilisateur> listeUtil = utilBd.getUtilisateurs();
            Utilisateur utili=null;
            for (Utilisateur util : listeUtil){
                if (util.getId()==rs.getInt("idut")){
                    utili=util;
                }
            }

            GestionVentes venteBD=new GestionVentes(laConnexion);
            List<Vente> listeVentes = venteBD.getVente();
            Vente vente=null;
            for (Vente vnt : listeVentes){
                if (vnt.getIdentifiant()==rs.getInt("idve")){
                    vente=vnt;
                }
            }
            Enchere enchere = new Enchere(dateHeure, prix, utili, vente, vente.getObjet());
            liste.add(enchere);
        }
        return liste;
    }
    public List<Enchere> getEncheresVente(Vente v) throws SQLException{
        PreparedStatement statement = this.laConnexion.prepareStatement("select * FROM ENCHERIR where idve = ?");
        statement.setInt(1, v.getIdentifiant());
        ResultSet rs= statement.executeQuery();
        List<Enchere> liste = new ArrayList<>();
        while(rs.next()){
            Date dateHeure=rs.getDate("dateheure");
            double prix=rs.getLong("montant");


            UtilisateurBd utilBd=new UtilisateurBd(laConnexion);
            List<Utilisateur> listeUtil = utilBd.getUtilisateurs();
            Utilisateur utili=null;
            for (Utilisateur util : listeUtil){
                if (util.getId()==rs.getInt("idut")){
                    utili=util;
                }
            }

            Enchere enchere = new Enchere(dateHeure, prix, utili, v, v.getObjet());
            liste.add(enchere);
        }
        return liste;
    }
}
