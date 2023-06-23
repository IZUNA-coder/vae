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

	/**
	 * Permet de se connecter à la base de données
	 * @param nomServeur
	 * @param nomBase
	 * @param nomLogin
	 * @param motDePasse
	 */
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

	/**
	 * Permet de fermer la base de données
	 */
	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}

	/**
	 * indique si nous somme connectés
	 * @return le boolean de si nous somme connectés
	 */
    public boolean isConnecte() { return this.connecte;}

	public Connection getConnection(){
		return this.mysql;
	}

	/**
	 * Permet de communiquer avec la base de donnés
	 * @param requete
	 * @return PreparedStatement
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
