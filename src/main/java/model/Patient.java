package model;

/**
 *
 * @author hhgyd
 */
public class Patient {
    
    private int id;
    private String cne;
    private String prenom;
    private String nom;
    private String phone;
    private int age;
    private String mail;

    public Patient() {
    }

    
    public Patient(String cne, String prenom, String nom, String phone, int age, String mail) {
        this.cne = cne;
        this.prenom = prenom;
        this.nom = nom;
        this.phone = phone;
        this.age = age;
        this.mail = mail;
    }
    
    public Patient(int id, String cne, String prenom, String nom, String phone, int age, String mail) {
        this.id = id;
        this.cne = cne;
        this.prenom = prenom;
        this.nom = nom;
        this.phone = phone;
        this.age = age;
        this.mail = mail;
    }
    

    
    // getters

    public int getId() {
        return id;
    }

    public String getCne() {
        return cne;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }
    
    // setters 

    public void setId(int id) {
        this.id = id;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    // toString
    @Override 
    public String toString() {
        return "Patient{ " + "ID patient = " + id + ", cne = " + cne
                + ", prénom = " + prenom + ", nom = " + nom + ", age = " + age + ", mail = " + mail + " }";
    }
    
}
