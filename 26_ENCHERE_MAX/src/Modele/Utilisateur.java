package Modele;

public class Utilisateur {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean actif;
    private Role role;
    
    // constructeurs
    public Utilisateur(){
        this.id=0;
        this.username="";
        this.password="";
        this.email="";
        this.actif=true;
        this.role=new Role(2);
    }
    /**
     * Constructeur de la classe Utilisateur
     * @param id
     * @param username
     * @param email
     * @param password
     * @param actif
     * @param role
     */
    public Utilisateur(int id,String username,String email,String password,boolean actif,Role role){
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.actif=actif;
        this.role=role;
    }
    /**
     * Constructeur de la classe Utilisateur
     * @param id
     * @param username
     * @param email
     * @param password
     * @param actif
     * @param idRole
     */
    public Utilisateur(int id,String username,String email,String password,boolean actif,int idRole){
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.actif=actif;
        this.role= new Role(idRole);
    }
    //

    // getters
    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean getActif(){
        return this.actif;
    }

    public Role getRole(){
        return this.role;
    }

    public int getIdRole(){    // (répétitif)
        return this.role.getIdRole();
    }

    public String getNomRole(){ // (répétitif)
        return this.role.getNomRole();
    }
    //

    // setters
    public void setId(int newId){
        this.id=newId;
    }

    public void setUsername(String newUsername){
        this.username=newUsername;
    }

    public void setPassword(String newPassword){
        this.password=newPassword;
    }

    public void setEmail(String newEmail){
        this.email=newEmail;
    }

    public void setActif(boolean actif){
        this.actif = actif;
    }

    public void setRole(Role newRole){
        this.role=newRole;
    }

    public void setIdRole(int newIdRole){       // (répétitif)
        this.role.setIdRole(newIdRole);
    }

    public void setNomRole(String newNomRole){  // (répétitif)
        this.role.setNomRole(newNomRole);
    }
    //
}
