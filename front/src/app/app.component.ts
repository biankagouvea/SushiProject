import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  styleUrls: ['./app.css'],
  template: `
    <header class="global-header">
      <div class="header-inner">
        <div class="brand-block">
          <h1 class="brand">Sushi Talence</h1>
        </div>

        <nav class="nav-actions" aria-label="Navigation principale">
          <a routerLink="/" class="nav-link">Accueil</a>
          <a routerLink="/menu" class="nav-link">Menu</a>

          @if (!auth.estConnecte()) {
            <a routerLink="/register" class="nav-link">S'inscrire</a>
            <a routerLink="/login" class="nav-link">Connexion</a>
          } @else {
            @if (auth.estAdmin()) {
              <a routerLink="/admin" class="nav-link admin-link">Admin</a>
            } @else {
              <span class="role-badge user">Client</span>
            }
            <button class="btn-logout" (click)="deconnexion()">Déconnexion</button>
          }
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