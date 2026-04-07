import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sushi } from '../sushi/sushi';

// Interface pour les articles d'une commande
export interface ArticleCommande {
  sushiId?: number;
  nom: string;
  prix: number;
  quantite: number;
}

// Interface pour une commande complète
export interface Commande {
  id?: number;
  nomClient: string;
  adresse: string;
  items: ArticleCommande[];
  total?: number;
}

@Injectable({
  providedIn: 'root'
})
export class SushiService {

  private baseUrl = 'http://localhost:8084/api';

  constructor(private http: HttpClient) {}

  // Récupérer la liste des sushis
  obtenirSushis(): Observable<Sushi[]> {
    return this.http.get<Sushi[]>(`${this.baseUrl}/sushis`);
  }

  // Créer une commande
  creerCommande(commande: Commande): Observable<any> {
    return this.http.post(`${this.baseUrl}/commandes`, commande);
  }
  
  // Récupérer toutes les commandes
  obtenirCommandes(): Observable<Commande[]> {
    return this.http.get<Commande[]>(`${this.baseUrl}/commandes`);
  }
}