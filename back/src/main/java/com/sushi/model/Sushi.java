package com.sushi.model;

public class Sushi {
	
	 private int id;
	 private String nom;
	 private double prix;
	 private String categorie;
	 private int stock;
	 private String image;
	 
	 public Sushi(){}
	 
	 public Sushi(int id, String nom, double prix, String categorie, int stock) {
	        this.id = id;
	        this.nom = nom;
	        this.prix = prix;
	        this.categorie = categorie;
	        this.stock = stock;
	    }

	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getNom() { return nom; }
	    public void setNom(String nom) { this.nom = nom; }

	    public double getPrix() { return prix; }
	    public void setPrix(double prix) { this.prix = prix; }

	    public String getCategorie() { return categorie; }
	    public void setCategorie(String categorie) { this.categorie = categorie; }

	    public int getStock() { return stock; }
	    public void setStock(int stock) { this.stock = stock; }
	    
	    public String getImage() {return image;}
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
