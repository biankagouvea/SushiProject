import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const auth = inject(AuthService);

  if (auth.estAdmin()) {
    return true;
  } else {
    window.alert('Accès administrateur requis');
    router.navigate(['/']);
    return false;
  }
};