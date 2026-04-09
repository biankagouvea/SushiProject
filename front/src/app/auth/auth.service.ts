import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly contextPath = this.detectContextPath();
  private readonly baseUrl = this.buildBaseUrl();
  private dernierMessage = '';

  constructor(
    private router: Router,
    private http: HttpClient
  ) {}

  private detectContextPath(): string {
    if (window.location.port === '4200') {
      return '';
    }

    const routeNames = new Set(['menu', 'login', 'register', 'admin']);
    const firstSegment = window.location.pathname.split('/').filter(Boolean)[0] || '';

    if (!firstSegment || routeNames.has(firstSegment)) {
      return '';
    }

    return `/${firstSegment}`;
  }

  private buildBaseUrl(): string {
    if (window.location.port === '4200') {
      return 'http://localhost:8084/api/auth';
    }

    return `${window.location.origin}${this.contextPath}/api/auth`;
  }

  getDernierMessage(): string {
    return this.dernierMessage;
  }

  connexion(email: string, motDePasse: string): Observable<boolean> {
    this.dernierMessage = '';
    return this.http.post<any>(`${this.baseUrl}/login`, {
      email,
      password: motDePasse
    }).pipe(
      tap((response: any) => {
        this.dernierMessage = response?.message || '';
        if (response?.success === true) {
          localStorage.setItem('loggedIn', 'true');
          localStorage.setItem('authToken', response.token || 'token');
          localStorage.setItem('authEmail', response.email || email);
          localStorage.setItem('authAdmin', response.isAdmin === true ? 'true' : 'false');
        }
      }),
      map((response: any) => response?.success === true),
      catchError(() => {
        this.dernierMessage = 'Serveur indisponible ou URL API incorrecte';
        return of(false);
      })
    );
  }

  inscription(email: string, motDePasse: string, isAdmin: boolean = false): Observable<boolean> {
    this.dernierMessage = '';
    return this.http.post<any>(`${this.baseUrl}/register`, {
      email,
      password: motDePasse,
      isAdmin
    }).pipe(
      tap((response: any) => {
        this.dernierMessage = response?.message || '';
      }),
      map((response: any) => response?.success === true),
      catchError(() => {
        this.dernierMessage = 'Serveur indisponible ou URL API incorrecte';
        return of(false);
      })
    );
  }

  estConnecte(): boolean {
    return localStorage.getItem('loggedIn') === 'true' && !!localStorage.getItem('authToken');
  }

  emailConnecte(): string | null {
    return localStorage.getItem('authEmail');
  }

  estAdmin(): boolean {
    return localStorage.getItem('authAdmin') === 'true';
  }

  deconnexion(): void {
    localStorage.removeItem('loggedIn');
    localStorage.removeItem('authToken');
    localStorage.removeItem('authEmail');
    localStorage.removeItem('authAdmin');
    this.router.navigate(['/login']);
  }

}