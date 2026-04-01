package com.sushi.model;

public class LigneCommande {

    private int sushiId;
    private String nom;
    private double prix;
    private int quantite;

    // Constructeur vide (obligatoire pour JSON)
    public LigneCommande() {}

    // Constructeur complet
    public LigneCommande(int sushiId, String nom, double prix, int quantite) {
        this.sushiId = sushiId;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    // ===== GETTERS =====
    public int getSushiId() {
        return sushiId;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    // ===== SETTERS =====
    public void setSushiId(int sushiId) {
        this.sushiId = sushiId;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}