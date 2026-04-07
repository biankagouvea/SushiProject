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
    console.log('Connexion:', this.email, this.password);
	if (!this.email || !this.password) {
	  alert('Veuillez remplir les champs');
	  return;
	}
	if (this.auth.connexion(this.email, this.password)) {
	    this.router.navigate(['/menu']);
	  } else {
	  alert('Erreur de connexion');
	}
  }
  
  allerAuRegister() {
    this.router.navigate(['/register']);
  }
}