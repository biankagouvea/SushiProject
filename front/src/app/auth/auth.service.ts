import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) {}

  connexion(email: string, motDePasse: string) {
    if (email && motDePasse) {
      localStorage.setItem('loggedIn', 'true');
      return true;
    }
    return false;
  }

  estConnecte(): boolean {
    return localStorage.getItem('loggedIn') === 'true';
  }

  deconnexion(): void {
    localStorage.removeItem('loggedIn');
    this.router.navigate(['/login']);
  }

}