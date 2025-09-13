// src/app/app.component.ts
import { Component, AfterViewInit } from '@angular/core';
import * as AOS from 'aos';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  cards = [
    {
      title: 'Matriz',
      description: 'Explore relationships in your data with our matrix visualization tool.',
      icon: 'fas fa-table',
      route: 'matriz',
      bgColor: 'bg-indigo-100',
      textColor: 'text-indigo-500',
      borderColor: 'hover:border-indigo-300',
      buttonBgColor: 'bg-indigo-50',
      buttonTextColor: 'text-indigo-600',
      pulseIcon: 'fas fa-plus'
    },
    {
      title: 'Sankey',
      description: 'Visualize flows and relationships between different entities in your data.',
      icon: 'fas fa-random',
      route: 'sankey',
      bgColor: 'bg-green-100',
      textColor: 'text-green-500',
      borderColor: 'hover:border-green-300',
      buttonBgColor: 'bg-green-50',
      buttonTextColor: 'text-green-600',
      pulseIcon: 'fas fa-bolt'
    },
    {
      title: 'Sankey History',
      description: 'Track the evolution of your Sankey diagrams over time with historical data.',
      icon: 'fas fa-history',
      route: 'sankey-history',
      bgColor: 'bg-purple-100',
      textColor: 'text-purple-500',
      borderColor: 'hover:border-purple-300',
      buttonBgColor: 'bg-purple-50',
      buttonTextColor: 'text-purple-600',
      pulseIcon: 'fas fa-star'
    }
  ];

  ngAfterViewInit() {
    const buttons = document.querySelectorAll('.btn-hover-effect');
    buttons.forEach((button, index) => {
      setTimeout(() => {
        (button as HTMLElement).style.opacity = '1';
        (button as HTMLElement).style.transform = 'translateY(0)';
      }, 100 * index);
    });
  }
}