package com.sushi.model;

/**
 * Modele representant un sushi du catalogue.
 */
public class Sushi {
	
	 private int id;
	 private String nom;
	 private double prix;
	 private String categorie;
	 private int stock;
	 private String image;
	 
	 /**
	  * Constructeur vide requis pour la deserialisation JSON.
	  */
	 public Sushi(){}
	 
	 /**
	  * Constructeur complet.
	  *
	  * @param id identifiant sushi
	  * @param nom nom du sushi
	  * @param prix prix unitaire
	  * @param categorie categorie (Maki/Nigiri/Sashimi)
	  * @param stock stock disponible
	  */
	 public Sushi(int id, String nom, double prix, String categorie, int stock) {
	        this.id = id;
	        this.nom = nom;
	        this.prix = prix;
	        this.categorie = categorie;
	        this.stock = stock;
	    }

	    /** @return identifiant unique du sushi */
	    public int getId() { return id; }
	    /** @param id identifiant unique du sushi */
	    public void setId(int id) { this.id = id; }

	    /** @return nom du sushi */
	    public String getNom() { return nom; }
	    /** @param nom nom du sushi */
	    public void setNom(String nom) { this.nom = nom; }

	    /** @return prix unitaire */
	    public double getPrix() { return prix; }
	    /** @param prix prix unitaire */
	    public void setPrix(double prix) { this.prix = prix; }

	    /** @return categorie du sushi */
	    public String getCategorie() { return categorie; }
	    /** @param categorie categorie du sushi */
	    public void setCategorie(String categorie) { this.categorie = categorie; }

	    /** @return stock disponible */
	    public int getStock() { return stock; }
	    /** @param stock stock disponible */
	    public void setStock(int stock) { this.stock = stock; }
	    
	    /** @return chemin ou URL d'image */
	    public String getImage() {return image;}
	    /** @param image chemin ou URL d'image */
	    public void setImage(String image) {this.image = image;}
	    
	    @Override
	    public String toString() {
	        return "Sushi{" +
	                "id=" + id +
	                ", nom='" + nom + '\'' +
	                ", prix=" + prix +
	                ", categorie='" + categorie + '\'' +
	                ", stock=" + stock +
	                '}';
	    }

}
