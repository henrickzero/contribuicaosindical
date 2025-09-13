import { Component, AfterViewInit } from '@angular/core';
import * as AOS from 'aos'; 
import * as feather from 'feather-icons';

interface Company {
  name: string;
  category: string;
  workers: number;
  image: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit {
  companies: Company[] = [
    { name: "Indústria Moderna", category: "Manufatura", workers: 250, image: "http://static.photos/industry/640x360/1" },
    { name: "Tecnologia Avançada", category: "TI", workers: 180, image: "http://static.photos/technology/640x360/2" },
    { name: "Construções Forte", category: "Construção", workers: 320, image: "http://static.photos/construction/640x360/3" },
    { name: "Comércio Justo", category: "Varejo", workers: 150, image: "http://static.photos/retail/640x360/4" },
    { name: "Serviços Integrados", category: "Serviços", workers: 210, image: "http://static.photos/office/640x360/5" },
    { name: "Alimentos Saudáveis", category: "Alimentação", workers: 190, image: "http://static.photos/food/640x360/6" },
    { name: "Logística Rápida", category: "Transporte", workers: 275, image: "http://static.photos/automotive/640x360/7" },
    { name: "Educação do Futuro", category: "Educação", workers: 130, image: "http://static.photos/education/640x360/8" },
    { name: "Saúde Integral", category: "Saúde", workers: 240, image: "http://static.photos/medical/640x360/9" }
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
      company.category.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  private replaceFeatherIcons() {
    if (typeof feather !== 'undefined') {
      feather.replace();
    }
  }
}