import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SushiService } from '../services/sushi.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.css']
})
export class MenuComponent implements OnInit {

  sushis: any[] = [];
  cart: any[] = [];
  totalGlobal: number = 0;

  selectedCategory: string = 'TOUS';

  nom: string = '';
  adresse: string = '';

  constructor(private sushiService: SushiService) {}

  // =====================
  // INIT
  // =====================
  ngOnInit(): void {
    this.sushiService.getSushis().subscribe({
      next: (data: any[]) => {
        this.sushis = data;

       
        this.sushis.forEach(s => s.qty = 0);
      },
      error: (err: any) => {
        console.error(err);
      }
    });
  }

  // =====================
  // FILTER
  // =====================
  filter(category: string) {
    this.selectedCategory = category;
  }

  // =====================
  // ADD TO CART
  // =====================
  ajouter(sushi: any): void {

    const qty = Number(sushi.qty);
    const prix = Number(sushi.prix);

    if (!qty || qty <= 0) {
      alert("Choisissez une quantité");
      return;
    }

    this.cart.push({
      nom: sushi.nom,
      prix: prix,
      quantite: qty
    });

    this.totalGlobal += prix * qty;

    console.log("cart:", this.cart);
    console.log("total:", this.totalGlobal);

    sushi.qty = 0;
  }

  // =====================
  // SUBMIT ORDER
  // =====================
  submitOrder(): void {

    if (this.cart.length === 0) {
      alert("Panier vide");
      return;
    }

    const order = {
      nomClient: this.nom,
      adresse: this.adresse,
      items: this.cart
    };

    this.sushiService.createOrder(order).subscribe({
      next: () => {
        alert("Commande envoyée");

        this.cart = [];
        this.totalGlobal = 0;
        this.nom = '';
        this.adresse = '';
      },
      error: () => {
        alert("Erreur commande");
      }
    });
  }

}