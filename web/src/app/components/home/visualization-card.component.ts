import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-visualization-card',
  templateUrl: './visualization-card.component.html',
  styleUrls: ['./visualization-card.component.css']
})
export class VisualizationCardComponent {
  @Input() title: string = '';
  @Input() description: string = '';
  @Input() icon: string = '';
  @Input() route: string = '';
  @Input() bgColor: string = '';
  @Input() textColor: string = '';
  @Input() borderColor: string = '';
  @Input() buttonBgColor: string = '';
  @Input() buttonTextColor: string = '';
  @Input() pulseIcon: string = '';

  constructor(private router: Router) {}

  navigate() {
    this.router.navigate([this.route]);
  }
}