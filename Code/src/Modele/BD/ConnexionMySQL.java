package Modele.BD;

import java.sql.*;

public class ConnexionMySQL {

	private Connection mysql;
	private boolean connecte=false;
	private LoginBD loginBD;

	public ConnexionMySQL() throws ClassNotFoundException, SQLException{
		this.mysql=null;
		this.connecte=false;
		Class.forName("org.mariadb.jdbc.Driver");
		this.loginBD=new LoginBD();
		connecter(loginBD.getNomServeur(), loginBD.getNomBD(), loginBD.getLogin(), loginBD.getMotDePasse());
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		try{
			this.mysql=null;
			this.connecte=false;
			this.mysql = DriverManager.getConnection(
						"jdbc:mysql://"+nomServeur+":3306/"+nomBase,nomLogin, motDePasse);
			this.connecte=true;
			if (this.mysql != null) {
				System.out.println("Connexion réussie à la base de données");
			}
		} catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception SQL ici
		}
	}

	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}

    public boolean isConnecte() { return this.connecte;}

	public Connection getConnection(){
		return this.mysql;
	}
	
}
