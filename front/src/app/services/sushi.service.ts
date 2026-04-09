import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
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
  userEmail?: string;
  nomClient: string;
  adresse: string;
  items: ArticleCommande[];
  total?: number;
  date?: string;
}

export interface NouveauSushi {
  nom: string;
  categorie: string;
  prix: number;
  image?: string;
  stock?: number;
}

@Injectable({
  providedIn: 'root'
})
export class SushiService {

  private readonly contextPath = this.detectContextPath();
  private readonly baseUrl = this.buildBaseUrl();

  constructor(private http: HttpClient) {}

  private detectContextPath(): string {
    if (window.location.port === '4200') {
      return '';
    }

    const routeNames = new Set(['menu', 'login', 'register', 'admin']);
    const firstSegment = window.location.pathname.split('/').filter(Boolean)[0] || '';

    if (!firstSegment || routeNames.has(firstSegment)) {
      return '';
    }

    return `/${firstSegment}`;
  }

  private buildBaseUrl(): string {
    if (window.location.port === '4200') {
      return 'http://localhost:8084/api';
    }

    return `${window.location.origin}${this.contextPath}/api`;
  }

  private resolveImageUrl(image?: string): string | undefined {
    if (!image) {
      return image;
    }

    if (image.startsWith('http://') || image.startsWith('https://') || image.startsWith('data:')) {
      return image;
    }

    const normalized = image.startsWith('/') ? image.substring(1) : image;
    return `${window.location.origin}${this.contextPath}/${normalized}`;
  }

  // Récupérer la liste des sushis
  obtenirSushis(): Observable<Sushi[]> {
    return this.http.get<Sushi[]>(`${this.baseUrl}/sushis`).pipe(
      map((sushis) => sushis.map((s) => ({
        ...s,
        image: this.resolveImageUrl(s.image)
      })))
    );
  }

  // Créer une commande
  creerCommande(commande: Commande): Observable<any> {
    return this.http.post(`${this.baseUrl}/commandes`, commande);
  }
  
  // Récupérer toutes les commandes
  obtenirCommandes(): Observable<Commande[]> {
    return this.http.get<Commande[]>(`${this.baseUrl}/commandes`);
  }

  // Récupérer l'historique d'un utilisateur
  obtenirHistoriqueCommandes(email: string): Observable<Commande[]> {
    return this.http.get<Commande[]>(`${this.baseUrl}/commandes/user/${encodeURIComponent(email)}`);
  }

  // Ajouter un nouveau sushi
  creerSushi(sushi: NouveauSushi): Observable<any> {
    return this.http.post(`${this.baseUrl}/sushis`, sushi);
  }
}