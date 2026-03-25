import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.css']
})
export class MenuComponent {
	


  // selecteur
  selectedCategory = 'TOUS';
  
  totalGlobal = 0;

  // donnees
  sushis = [
     {
       name: 'Maki Saumon',
       price: 6.5,
       category: 'MAKI',
       image: 'https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=400',
       qty: 0
     },
     {
       name: 'Nigiri Thon',
       price: 7.0,
       category: 'NIGIRI',
       image: 'https://images.unsplash.com/photo-1611143669185-af224c5e3252?w=400',
       qty: 0
     }
   ];
   
   nom = '';
   adresse = '';

   filter(cat: string) {
     this.selectedCategory = cat;
   }

   ajouter(nom: string, prix: number, qty: number) {

     if (qty <= 0) {
       alert("Veuillez choisir une quantité");
       return;
     }

     this.totalGlobal += prix * qty;
     alert(qty + "x " + nom + " ajouté !");
   }


   submitOrder() {
     if (this.totalGlobal === 0) {
       alert("Votre panier est vide");
     } else {
       alert("Commande confirmée pour " + this.totalGlobal.toFixed(2) + " €");
     }
   }
}