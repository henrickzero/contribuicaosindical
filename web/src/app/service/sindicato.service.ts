import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SindicatoService {
  private apiUrl = 'https://api.seu-backend.com/sindicatos'; // Ajuste para a URL real da API

  constructor(private http: HttpClient) {}

  buscarSindicatos(query: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/search?query=${query}`);
  }

  getSindicatoById(id: string | null): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}