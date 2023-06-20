package Modele;

public class Role {
    private int idRole;
    private String nomRole;
    private final static String[] NOMROLES_STRINGS = {"Administrateur","Utilisateur"};

    //constructeurs
    public Role(int id, String nom){
        this.idRole=id;
        this.nomRole=nom;
    }

    public Role(int id){
        this.idRole=id;
        this.nomRole=NOMROLES_STRINGS[id-1];
    }

    public Role(String nomRole){
        this.idRole=getIdRole(nomRole);
        this.nomRole=nomRole;
    }
    //

    //getters
    public int getIdRole(){
        return this.idRole;
    }

    public int getIdRole(String nomRole){
        int id=0;
        for(int i=0;i<NOMROLES_STRINGS.length;i++){
            if(nomRole.equals(NOMROLES_STRINGS[i])){
                id=i+1;
            }
        }
        return id;
    }

    public String getNomRole(){
        return this.nomRole;
    }

    
    //

    //setters
    public void setIdRole(int newIdRole){
        this.idRole=newIdRole;
    }

    public void setNomRole(String newNomRole){
        this.nomRole=newNomRole;
    }
    //
}
