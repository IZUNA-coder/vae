package Modele.BD;

// improt pour le check email
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modele.Utilisateur;


public class InscriptionUtilisateur {
    private Utilisateur user;

    public InscriptionUtilisateur(Utilisateur u){
        this.user=u;
    }


    public boolean checkPassword(){
        String password = user.getPassword();
        int numbers = 0;
        if(password.length()<8){
            return false;
        }
        for(int i=0; i<password.length(); i++){
            char c = password.charAt(i);
            if(Character.isDigit(c)){
                numbers+=1;
            }
        }
        if(numbers<1){
            return false;
        }
        return true;
    }

    public boolean checkDonnéeVide(){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()){
            return true;
        }
        return false;
    }

    public boolean checkEmail(){
        String email = user.getEmail();
        // Pattern pour vérifier le format de l'adresse email
        String emailPattern = "^[A-Za-z][A-Za-z0-9._-]*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";
        
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
}