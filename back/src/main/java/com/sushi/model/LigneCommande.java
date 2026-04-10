package com.sushi.model;

/**
 * Ligne d'une commande (un sushi, son prix et sa quantite).
 */
public class LigneCommande {

    private int sushiId;
    private String nom;
    private double prix;
    private int quantite;

    /**
     * Constructeur vide requis pour la deserialisation JSON.
     */
    public LigneCommande() {}

    /**
     * Constructeur complet.
     *
     * @param sushiId identifiant du sushi
     * @param nom nom du sushi
     * @param prix prix unitaire au moment de la commande
     * @param quantite quantite commandee
     */
    public LigneCommande(int sushiId, String nom, double prix, int quantite) {
        this.sushiId = sushiId;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    /** @return identifiant du sushi */
    public int getSushiId() {
        return sushiId;
    }

    /** @return nom du sushi */
    public String getNom() {
        return nom;
    }

    /** @return prix unitaire */
    public double getPrix() {
        return prix;
    }

    /** @return quantite commandee */
    public int getQuantite() {
        return quantite;
    }

    /** @param sushiId identifiant du sushi */
    public void setSushiId(int sushiId) {
        this.sushiId = sushiId;
    }

    /** @param nom nom du sushi */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** @param prix prix unitaire */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /** @param quantite quantite commandee */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}