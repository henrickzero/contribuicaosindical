import { Component, AfterViewInit } from '@angular/core';
import * as AOS from 'aos'; 
import * as feather from 'feather-icons';

interface Company {
  id: string;
  idSindicato: string;
  name: string;
  nameSindicato: string;
  municipio: string;
  uf: string;
  image: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit {
  companies: Company[] = [
    { id: "dataprev", idSindicato: "sindpdpb", name: "Dataprev", nameSindicato: "SINDPDPB", municipio: "João Pessoa", uf: "PB", image: "http://static.photos/industry/640x360/1" },
    { id: "serpro", idSindicato: "sindpdpb", name: "Serpro", nameSindicato: "SINDPDPB", municipio: "João Pessoa", uf: "PB", image: "http://static.photos/technology/640x360/2" },
    { id: "soluti", idSindicato: "sindpdpb", name: "Soluti", nameSindicato: "SINDPDPB", municipio: "João Pessoa", uf: "PB", image: "http://static.photos/construction/640x360/3" },
    { id: "casas-pio", idSindicato: "sindpdpb", name: "Casas Pio", nameSindicato: "SINDPDPB", municipio: "João Pessoa", uf: "PB", image: "http://static.photos/retail/640x360/4" },
    { id: "casas-pedro", idSindicato: "sindpdrj", name: "Casas Pedro", nameSindicato: "SINDPDRJ", municipio: "Rio de Janeiro", uf: "RJ", image: "http://static.photos/office/640x360/5" },
    { id: "serpro", idSindicato: "sindpdrj", name: "Serpro", nameSindicato: "SINDPDRJ", municipio: "Rio de Janeiro", uf: "RJ", image: "http://static.photos/food/640x360/6" },
    { id: "dataprev", idSindicato: "sindpdrj", name: "Logística Rápida", nameSindicato: "SINDPDRJ", municipio: "Rio de Janeiro", uf: "RJ", image: "http://static.photos/automotive/640x360/7" },
    { id: "casa-pio", idSindicato: "sindpdsc", name: "Casas Pio", nameSindicato: "SINDPDSC", municipio: "Florianopolis", uf: "SC", image: "http://static.photos/education/640x360/8" },
    { id: "soluti", idSindicato: "sindpdsc", name: "Saúde Integral", nameSindicato: "SINDPDSC", municipio: "Florianopolis", uf: "SC", image: "http://static.photos/medical/640x360/9" }
  ];

  filteredCompanies: Company[] = [...this.companies];
  searchTerm: string = '';

  ngAfterViewInit() {
    AOS.init({
      duration: 800,
      easing: 'ease-in-out',
      once: true,
    });
    this.replaceFeatherIcons();
  }

  filterCompanies() {
    this.filteredCompanies = this.companies.filter(company =>
      company.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      company.nameSindicato.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  private replaceFeatherIcons() {
    if (typeof feather !== 'undefined') {
      feather.replace();
    }
  }
}