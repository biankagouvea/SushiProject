package com.sushi.model;

import java.util.List;

/**
 * Modele representant une commande client.
 */
public class Commande {
	
	private int id;
    private String nomClient;
    private String adresse;
    private String date;
    private double total;
    private List<LigneCommande> items;

	/**
	 * Constructeur vide requis pour la deserialisation JSON.
	 */
	public Commande() {}

    /**
     * Constructeur complet.
     *
     * @param id identifiant commande
     * @param nomClient nom du client
     * @param adresse adresse de livraison
     * @param date date ISO de creation
     * @param total montant total
     * @param items lignes de commande
     */
    public Commande(int id, String nomClient, String adresse, String date, double total, List<LigneCommande> items) {
        this.id = id;
        this.nomClient = nomClient;
        this.adresse = adresse;
        this.date = date;
        this.total = total;
        this.items = items;
    }

    /** @return identifiant de la commande */
    public int getId() { return id; }
    /** @param id identifiant de la commande */
    public void setId(int id) { this.id = id; }

    /** @return nom du client */
    public String getNomClient() { return nomClient; }
    /** @param nomClient nom du client */
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    /** @return adresse de livraison */
    public String getAdresse() { return adresse; }
    /** @param adresse adresse de livraison */
    public void setAdresse(String adresse) { this.adresse = adresse; }

    /** @return date ISO de la commande */
    public String getDate() { return date; }
    /** @param date date ISO de la commande */
    public void setDate(String date) { this.date = date; }

    /** @return montant total */
    public double getTotal() { return total; }
    /** @param total montant total */
    public void setTotal(double total) { this.total = total; }

    /** @return lignes de commande */
    public List<LigneCommande> getItems() { return items; }
    /** @param items lignes de commande */
    public void setItems(List<LigneCommande> items) { this.items = items; }

}
