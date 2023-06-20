package Modele;

import java.util.Comparator;

public class ComparateurEnchere implements Comparator<Enchere>{
    @Override
    public int compare(Enchere e1, Enchere e2){
        return Double.compare(e1.getPrix(), e2.getPrix());
    }
    
}
