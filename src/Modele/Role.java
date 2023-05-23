package Modele;

public class Role {
    private int idRole;
    private String nomRole;

    //constructeurs
    public Role(int id, String nom){
        this.idRole=id;
        this.nomRole=nom;
    }

    public Role(int id){
        this.idRole=id;
        this.nomRole=getNomRole(id);
    }
    //

    //getters
    public int getIdRole(){
        return this.idRole;
    }

    public String getNomRole(){
        return this.nomRole;
    }

    public String getNomRole(int idRole){
        if(idRole==1){
            return "Administrateur";
        } else{return "Utilisateur";}
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
