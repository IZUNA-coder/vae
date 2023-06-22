package Modele.BD;

public class LoginBD{
    private String nomServer;
    private String nomBD;
    private String login;
    private String password;
   
    public LoginBD(){
        // Connexion en localHost
        this.nomServer="localhost";
        this.nomBD="VAE";
        this.login="root";
        this.password="Matthias_crx1";

        // Connexion à l'iut

        // this.nomServer="servinfo-mariadb";
        // this.nomBD="DBcoursimault";
        // this.login="coursimault";
        // this.password="coursimault";
    }

    public String getMotDePasse() {
        return this.password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getNomServeur() {
        return this.nomServer;
    }

    public String getNomBD() {
        return this.nomBD;
    }

}
