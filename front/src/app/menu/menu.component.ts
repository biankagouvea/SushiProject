import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SushiService, Commande, ArticleCommande } from '../services/sushi.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.css']
})
export class MenuComponent implements OnInit {

  sushis: any[] = [];
  panier: any[] = [];
  totalGlobal: number = 0;

  categorieSelectionnee: string = 'TOUS';

  nomClient: string = '';
  adresse: string = '';

  messageSuccesText: string | null = null; // texte success affiché dans le toast
  messageErreurText: string | null = null;
  envoiEnCours: boolean = false;

  constructor(private sushiService: SushiService) {}

  // =====================
  // INITIALISATION
  // =====================
  ngOnInit(): void {
    this.chargerSushis();
  }

  chargerSushis(): void {
    this.sushiService.obtenirSushis().subscribe({
      next: (donnees: any[]) => {
        this.sushis = donnees;
        // Initialiser quantité à 0
        this.sushis.forEach(s => s.qty = 0);
      },
      error: (err: any) => {
        console.error('Erreur chargement sushis:', err);
        this.messageErreurText = 'Impossible de charger les sushis';
      }
    });
  }

  // =====================
  // FILTRER
  // =====================
  filtrer(categorie: string) {
    this.categorieSelectionnee = categorie;
  }

  // =====================
  // AJOUTER AU PANIER
  // =====================
  ajouterAuPanier(sushi: any): void {
    const qte = Number(sushi.qty);
    const prix = Number(sushi.prix);

    if (!qte || qte <= 0) {
      alert("Veuillez choisir une quantité");
      return;
    }

    this.panier.push({
      sushiId: sushi.id,
      nom: sushi.nom,
      prix: prix,
      quantite: qte
    });

    this.totalGlobal += prix * qte;

    console.log("Panier mis à jour:", this.panier);
    console.log("Total:", this.totalGlobal);

    sushi.qty = 0;
  }

  // =====================
  // SOUMETTRE COMMANDE
  // =====================
  soumettreCommande(): void {
    // Validation
    if (this.panier.length === 0) {
      alert("Panier vide - ajoutez des sushis avant de commander");
      return;
    }

    if (!this.nomClient || this.nomClient.trim() === '') {
      alert("Veuillez entrer votre nom");
      return;
    }

    if (!this.adresse || this.adresse.trim() === '') {
      alert("Veuillez entrer votre adresse");
      return;
    }

    // Préparer la commande
    const commande: Commande = {
      nomClient: this.nomClient,
      adresse: this.adresse,
      items: this.panier
    };

  this.envoiEnCours = true;
  this.messageSuccesText = null;
  this.messageErreurText = null;

    // Envoyer au backend
    this.sushiService.creerCommande(commande).subscribe({
      next: (reponse: any) => {
        console.log('Commande envoyée avec succès:', reponse);
        // Afficher succès (si backend retourne un id, l'inclure)
        this.envoiEnCours = false;
        const id = reponse && reponse.id ? reponse.id : null;
        this.messageSuccesText = id ? `Commande ${id} envoyée avec succès !` : 'Commande envoyée avec succès ! Merci pour votre achat.';

        // Réinitialiser après 2 secondes (mais laisser le message affiché, l'utilisateur peut fermer)
        setTimeout(() => {
          this.panier = [];
          this.totalGlobal = 0;
          this.nomClient = '';
          this.adresse = '';
        }, 2000);
      },
      error: (err: any) => {
        console.error('Erreur envoi commande:', err);
        this.messageErreurText = 'Erreur lors de l\'envoi de la commande. Veuillez réessayer.';
        this.envoiEnCours = false;
      }
    });
  }

  // Fermer les messages de feedback
  fermerFeedback(): void {
    this.messageSuccesText = null;
    this.messageErreurText = null;
  }

  // Retirer un item du panier
  retirerDuPanier(index: number): void {
    if (index < 0 || index >= this.panier.length) return;
    const item = this.panier[index];
    const montant = Number(item.prix) * Number(item.quantite);
    this.totalGlobal = Math.max(0, this.totalGlobal - montant);
    this.panier.splice(index, 1);
  }
}