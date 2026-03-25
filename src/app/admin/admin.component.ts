import { Component } from '@angular/core';
import{Router} from'@angular/router';

@Component({
  selector: 'app-admin',
  standalone: true,
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.css']
})
export class AdminComponent {
	
	onSubmit(event: any) {
	    event.preventDefault();

	    const nom = event.target.nom.value;

	    alert("Le produit " + nom + " a été enregistré");

	    event.target.reset();
	  }
	  constructor(private router: Router) {}

	  goMenu() {
	    this.router.navigate(['/menu']);
	  }
}