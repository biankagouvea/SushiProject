import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);

  const estConnecte = localStorage.getItem('loggedIn');

  if (estConnecte === 'true') {
    return true;
  } else {
    window.alert('Veuillez vous connecter');
    router.navigate(['/login']);
    return false;
  }
};