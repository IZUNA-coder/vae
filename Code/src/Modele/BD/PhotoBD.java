package Modele.BD;

import java.sql.*;
import java.util.Random;

import Modele.Vente;
public class PhotoBD {
    private Connection laConnexion;
	public PhotoBD(Connection laConnexion){
		this.laConnexion= laConnexion;
		try{
			this.laConnexion.createStatement();
		}catch(Exception exep){
			System.out.println(exep);
		}
		
        
	}


    public void setPhoto(Vente v){
        String type = v.getObjet().getCategorie().getNom();
        int idob = v.getIdObjet();
        String titreph = "img" + idob;
        String query2 = "DELETE FROM PHOTO WHERE idph = ?";
        String query = "INSERT INTO PHOTO (idph, titreph, imgph, idob) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement2 = laConnexion.prepareStatement(query2);
            statement2.setInt(1, idob);
            statement2.executeQuery();
            PreparedStatement statement = laConnexion.prepareStatement(query);
            statement.setInt(1, idob);
            statement.setString(2, titreph);
            statement.setInt(4, idob);

            Random random = new Random();
            int randomNumber = random.nextInt(3) + 1;

            if (type.equals("Vêtement")){
                statement.setString(3, "file:ressources/img/imgObjet/vetement/img" + randomNumber + ".jpg");
            }
            else if(type.equals("Ustensile Cuisine")){
                statement.setString(3, "file:ressources/img/imgObjet/ustensile/img" + randomNumber + ".jpg");
            }
            else if(type.equals("Meuble")){
                statement.setString(3, "file:ressources/img/imgObjet/meuble/img" + randomNumber + ".jpg");
            }
            else if(type.equals("Outil")){
                statement.setString(3, "file:ressources/img/imgObjet/outil/img" + randomNumber + ".jpg");
            }
            else{
                statement.setString(3, "file:ressources/img/imgObjet/nonphoto.jpeg");
            }
            statement.executeUpdate();
        }catch(Exception exep){
            System.out.println(exep);
        }
    }
    
    public String getUrlPhoto(int idob) throws SQLException{
        String res="";
        PreparedStatement st = this.laConnexion.prepareStatement("SELECT imgph FROM PHOTO WHERE idob = ?");
        st.setInt(1, idob);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            res= rs.getString("imgph");
        }
        return res;
    }
}
