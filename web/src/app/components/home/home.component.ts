import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SindicatoService } from 'src/app/service/sindicato.service';

@Component({
  selector: 'home-header',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  searchQuery: string | null = '';
  searchResults: any[] = []; // Tipo deve ser ajustado para o modelo de Sindicato

  constructor(private sindicatoService: SindicatoService, private router: Router) {}

  searchSindicatos(query: any) {
    this.searchQuery = query;
    if (query.length >= 3) { // Busca mínima de 3 caracteres para otimizar
      this.sindicatoService.buscarSindicatos(query).subscribe(results => {
        this.searchResults = results;
      });
    } else {
      this.searchResults = [];
    }
  }

  navigateToSindicato(id: number) {
    this.router.navigate(['/sindicato', id]);
  }
}