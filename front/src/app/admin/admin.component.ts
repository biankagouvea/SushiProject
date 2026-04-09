import { Component, OnInit, OnDestroy, NgZone, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SushiService, Commande, NouveauSushi } from '../services/sushi.service';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.css']
})
export class AdminComponent implements OnInit, OnDestroy {

  commandes: Commande[] = [];
  intervalId: any;
  chargementEnCours: boolean = false;
  displayedDetails: number | null = null;

  constructor(
    private router: Router,
    private sushiService: SushiService,
    private authService: AuthService,
    private ngZone: NgZone,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.chargerCommandes();

    // Polling chaque 3 secondes pour mettre à jour les commandes
    this.intervalId = setInterval(() => {
      this.chargerCommandes();
    }, 3000);
  }

  ngOnDestroy(): void {
    // Nettoyer l'intervalle au destruction du composant
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  chargerCommandes(): void {
    this.chargementEnCours = true;
    console.log('🔄 Chargement admin - commandes...');
    this.sushiService.obtenirCommandes().subscribe({
      next: (donnees: Commande[]) => {
        console.log('✅ Admin - Commandes reçues:', donnees.length, 'commandes');
        this.commandes = donnees;
        this.chargementEnCours = false;
        console.log('📋 Admin - Commandes affichées:', this.commandes.length);
        // Force la détection de changement Angular
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.error("❌ Admin - Erreur chargement commandes:", err);
        this.chargementEnCours = false;
      }
    });
  }

  toggleDetails(commandeId: number): void {
    this.displayedDetails = this.displayedDetails === commandeId ? null : commandeId;
  }

  allerAuMenu(): void {
    this.router.navigate(['/menu']);
  }

  seDeconnecter(): void {
    if (confirm('Êtes-vous sûr de vouloir vous déconnecter ?')) {
      this.authService.deconnexion();
    }
  }

  ajouterSushi(event: any): void {
    event.preventDefault();

    const nom = event.target.nom.value;
    const categorie = event.target.categorie.value;
    const prix = Number(event.target.prix.value);
    const image = event.target.image.value;

    if (!nom || !categorie || !prix || prix <= 0) {
      alert("Veuillez remplir correctement les champs");
      return;
    }

    const nouveauSushi: NouveauSushi = {
      nom,
      categorie,
      prix,
      image,
      stock: 10
    };

    this.sushiService.creerSushi(nouveauSushi).subscribe({
      next: () => {
        alert("Sushi ajouté avec succès");
        event.target.reset();
      },
      error: (err: any) => {
        console.error("Erreur ajout sushi:", err);
        alert("Erreur lors de l'ajout du sushi");
      }
    });
  }
}

