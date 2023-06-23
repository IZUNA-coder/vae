package Modele.BD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginBD{
    private String nomServer;
    private String nomBD;
    private String login;
    private String password;
    /**
     * Constructeur de la classe LoginBD
     */
    public LoginBD() {
        String filePath = "loginBD.txt";
        System.out.println("Traitement du fichier de configuration de la BD");
        System.out.println("----------------------------");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int nbLigne = 1; // Commencer à partir de la ligne 1
            while ((line = br.readLine()) != null) {
                if (nbLigne >= 10) { // Commencer à traiter les lignes à partir de la ligne 10
                    // Traitez chaque ligne ici
                    String data = line.substring(0, line.length() - 1).trim();
                    System.out.println(data);
                    // Assignez les valeurs aux variables appropriées en fonction du numéro de ligne
                    int ligneCourante = nbLigne - 10; // Ajuster l'indice de ligne courante
                    if (ligneCourante == 0) {
                        this.nomServer = data;
                    } else if (ligneCourante == 1) {
                        this.nomBD = data;
                    } else if (ligneCourante == 2) {
                        this.login = data;
                    } else if (ligneCourante == 3) {
                        this.password = data;
                    }
                }
                
                nbLigne++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------");
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
