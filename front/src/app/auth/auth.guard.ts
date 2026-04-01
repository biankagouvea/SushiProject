import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const router = new Router();

  const isLoggedIn = localStorage.getItem('loggedIn');

  if (isLoggedIn === 'true') {
    return true;
  } else {
    window.alert('Veuillez vous connecter');
    router.navigate(['/login']);
    return false;
  }
};