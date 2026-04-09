import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {

  email = '';
  password = '';
  isAdmin = false;

  constructor(
    private router: Router,
    private auth: AuthService
  ) {}

  register() {
    const email = this.email.trim().toLowerCase();
    const password = this.password.trim();

    if (!email || !password) {
      alert('Veuillez remplir les champs');
      return;
    }

    this.auth.inscription(email, password, this.isAdmin).subscribe({
      next: (ok: boolean) => {
        if (ok) {
          alert('Inscription reussie !');
          this.router.navigate(['/login']);
        } else {
          alert(this.auth.getDernierMessage() || 'Inscription impossible');
        }
      },
      error: () => {
        alert(this.auth.getDernierMessage() || 'Serveur indisponible');
      }
    });
  }
}