import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
})
export class RegisterComponent {

  email = '';
  password = '';

  constructor(private router: Router) {}

  register() {
    alert('Inscription réussie !');
    this.router.navigate(['/login']);
  }
}