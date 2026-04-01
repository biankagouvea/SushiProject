package com.sushi.model;

import java.util.List;

public class Commande {
	
	private int id;
    private String nomClient;
    private String adresse;
    private String date;
    private double total;
    private List<LigneCommande> items;
	public Commande() {}

    public Commande(int id, String nomClient, String adresse, String date, double total, List<LigneCommande> items) {
        this.id = id;
        this.nomClient = nomClient;
        this.adresse = adresse;
        this.date = date;
        this.total = total;
        this.items = items;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<LigneCommande> getItems() { return items; }
    public void setItems(List<LigneCommande> items) { this.items = items; }

}
