import java.util.Comparator;

public class ComparateurEnchere implements Comparator<Enchere>{
    @Override
    public int compare(Enchere e1, Enchere e2){
        return e1.getPrix()-e2.getPrix();
    }
    
}
