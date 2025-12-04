package model;

/**
 *
 * @author hhgyd
 */
public class CategorieConsultation {
    
    private int id; 
    private String categorie;

    //empty constructor
    public CategorieConsultation() {
    }

    public CategorieConsultation(String categorie) {
        this.categorie = categorie;
    }

    public CategorieConsultation(int id, String categorie) {
        this.id = id;
        this.categorie = categorie;
    }
    
    // getters

    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }
    
    
    // setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "CategorieConsultation{" + "id=" + id + ", categorie=" + categorie + '}';
    }
    
    
    
}
