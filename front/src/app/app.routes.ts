import { Routes } from '@angular/router';
import  {HomeComponent} from './home/home.component';
import  {MenuComponent} from './menu/menu.component';
import {LoginComponent} from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import {RegisterComponent} from './register/register.component';
import {authGuard} from './auth/auth.guard';



export const routes: Routes = [
	 { path: '', component: HomeComponent },          // page d'accueil
	 { path: 'login', component: LoginComponent },    // connexion
	 {path:'register', component:RegisterComponent}, // s'inscrire
	 { path: 'menu', component: MenuComponent },      // passer commande
	 { path: 'admin', component: AdminComponent, canActivate: [authGuard] }     // page d'admin (protégée)
];
