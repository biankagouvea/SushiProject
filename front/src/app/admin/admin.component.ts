import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SushiService, Commande } from '../services/sushi.service';
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

  constructor(
    private router: Router,
    private sushiService: SushiService,
    private authService: AuthService
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
    this.sushiService.obtenirCommandes().subscribe({
      next: (donnees: Commande[]) => {
        console.log("Commandes reçues:", donnees);
        this.commandes = donnees;
        this.chargementEnCours = false;
      },
      error: (err: any) => {
        console.error("Erreur chargement commandes:", err);
        this.chargementEnCours = false;
      }
    });
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
    const prix = event.target.prix.value;
    const image = event.target.image.value;

    console.log("Sushi à ajouter:", { nom, categorie, prix, image });

    alert("Fonction 'Ajouter Sushi' non implémentée (à faire au backend)");

    event.target.reset();
  }
}

