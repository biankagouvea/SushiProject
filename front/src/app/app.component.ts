import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  template: `
    <header class="global-header">
      <div class="header-inner">
  <h1 class="brand">Sushi Talence</h1>

        <nav class="nav-actions">
          <a routerLink="/" class="nav-link">Accueil</a>
          <a routerLink="/menu" class="nav-link">Menu</a>
          <a routerLink="/register" *ngIf="!auth.estConnecte()" class="nav-link">S'inscrire</a>
          <a routerLink="/login" *ngIf="!auth.estConnecte()" class="nav-link">Connexion</a>

          <button *ngIf="auth.estConnecte()" class="btn-logout" (click)="deconnexion()">Déconnexion</button>
        </nav>
      </div>
    </header>

    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  constructor(public auth: AuthService) {}

  deconnexion(): void {
    if (confirm('Voulez-vous vous déconnecter ?')) {
      this.auth.deconnexion();
    }
  }
}