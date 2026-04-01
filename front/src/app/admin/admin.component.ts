import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SushiService } from '../services/sushi.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.css']
})
export class AdminComponent implements OnInit {

  commandes: any[] = [];

  constructor(
    private router: Router,
    private sushiService: SushiService
  ) {}

  ngOnInit(): void {
    this.loadCommandes();

   
    setInterval(() => {
      this.loadCommandes();
    }, 2000);
  }

  loadCommandes(): void {
    this.sushiService.getCommandes().subscribe({
      next: (data: any[]) => {
        console.log("commandes:", data);
        this.commandes = data;
      },
      error: (err: any) => {
        console.error("Erreur chargement commandes", err);
      }
    });
  }

  goMenu(): void {
    this.router.navigate(['/menu']);
  }

  onSubmit(event: any): void {
    event.preventDefault();

    const nom = event.target.nom.value;

    console.log("Sushi ajouté:", nom);

    alert("Sushi ajouté (fake)");

    event.target.reset();
  }
}
