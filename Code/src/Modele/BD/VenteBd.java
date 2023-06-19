import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Modele.Categorie;
import Modele.Objet;
import Modele.Statut;
import Modele.Utilisateur;
import Modele.Vente;


public class VenteBd {
    private ConnexionMySQL laConnexion;
    private Statement st;

    public VenteBd(ConnexionMySQL laConnexion){
        this.laConnexion=laConnexion;
        try{
            this.st=this.laConnexion.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insereVente(Vente vente) throws SQLException{
        PreparedStatement ps=this.laConnexion.prepareStatement("insert into OBJET  values (?,?,?,?,?)");
        ps.setInt(1, vente.getIdentifiant());
        ps.setLong(2, vente.getPrixBase());
        ps.setLong(3, vente.getPrixMin());
        ps.setDate(4, vente.getDebutVente());
        ps.setDate(5, vente.getFinVente());
        ps.setInt(6, vente.getObjet().getIdentifiant());
        ps.setInt(7, vente.getStatut().getIdentifiant());

        
        
        ps.executeUpdate();
    }

    public List<Vente> getVente() throws SQLException{
        ResultSet rs= this.laConnexion.prepareStatement("select * from OBJET").executeQuery();
        List<Vente> liste=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("idve");
            double prixBase=rs.getLong("prixbase");
            double prixMin=rs.getLong("prixmin");
            Date debut=rs.getDate("debutve");
            Date fin=rs.getDate("finve");

            ObjetBd objBd=new ObjetBd(laConnexion);
            List<Objet> listeObjet = objBd.getObjets();
            Objet obj=null;
            for (Objet objet : listeObjet){
                if (objet.getIdentifiant()==rs.getInt("idob")){
                    obj=objet;
                }
            }

            StatutBD stBD=new StatutBD(laConnexion);
            List<Statut> listeST = stBD.listeStatut();
            Statut stat=null;
            for (Statut st : listeST){
                if (st.getIdentifiant()==rs.getInt("idst")){
                    stat=st;
                }
            }
            Vente vente= new Vente(id, prixBase, prixMin, debut, fin, stat, obj);
            liste.add(vente);
        }
        return liste;
    }
}
