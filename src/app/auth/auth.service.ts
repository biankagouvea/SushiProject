import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  login(email: string, password: string) {
    if (email && password) {
      localStorage.setItem('loggedIn', 'true');
      return true;
    }
    return false;
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('loggedIn') === 'true';
  }

  logout() {
    localStorage.removeItem('loggedIn');
  }

}