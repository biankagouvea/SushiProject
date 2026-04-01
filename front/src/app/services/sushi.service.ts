import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sushi } from '../sushi/sushi';

@Injectable({
  providedIn: 'root'
})
export class SushiService {

  private sushiUrl = 'http://localhost:8084/api/sushis';
  private orderUrl = 'http://localhost:8084/api/commandes';

  constructor(private http: HttpClient) {}

  
  getSushis(): Observable<Sushi[]> {
    return this.http.get<Sushi[]>(this.sushiUrl);
  }

  
  createOrder(order: any): Observable<any> {
    return this.http.post(this.orderUrl, order);
  }
  
  getCommandes(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8084/api/commandes');
  }
}