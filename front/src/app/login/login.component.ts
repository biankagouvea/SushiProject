import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service'; 


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  email = '';
  password = '';
  
  constructor(
	private router: Router,
	private auth: AuthService
  ) {} //constructeur
  
  connexion() {
	const email = this.email.trim().toLowerCase();
	const password = this.password.trim();

	if (!email || !password) {
	  alert('Veuillez remplir les champs');
	  return;
	}

  this.auth.connexion(email, password).subscribe({
    next: (ok: boolean) => {
      if (ok) {
        this.router.navigate(['/menu']);
      } else {
        alert(this.auth.getDernierMessage() || 'Email ou mot de passe incorrect');
      }
    },
    error: () => {
      alert(this.auth.getDernierMessage() || 'Serveur indisponible ou URL API incorrecte');
    }
  });
  }
  
  allerAuRegister() {
    this.router.navigate(['/register']);
  }
}