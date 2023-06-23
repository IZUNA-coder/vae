package Modele.BD;

import java.sql.*;

public class ConnexionMySQL {

	private Connection mysql;
	private boolean connecte=false;
	private LoginBD loginBD;
	/**
	 * Constructeur de la classe ConnexionMySQL
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ConnexionMySQL() throws ClassNotFoundException, SQLException{
		this.mysql=null;
		this.connecte=false;
		Class.forName("org.mariadb.jdbc.Driver");
		this.loginBD=new LoginBD();
		connecter(loginBD.getNomServeur(), loginBD.getNomBD(), loginBD.getLogin(), loginBD.getMotDePasse());
	}
	/**
	 * Méthode qui permet de se connecter à la base de données
	 * @param nomServeur
	 * @param nomBase
	 * @param nomLogin
	 * @param motDePasse
	 * @throws SQLException
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
	 * Méthode qui permet de se déconnecter de la base de données
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}
	/**
	 * Méthode qui permet de savoir si on est connecté à la base de données
	 * @return
	 */
    public boolean isConnecte() { return this.connecte;}

	public Connection getConnection(){
		return this.mysql;
	}
	/**
	 * Méthode qui permet de préparer une requête
	 * @param requete
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
