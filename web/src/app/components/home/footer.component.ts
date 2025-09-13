import { Component, AfterViewInit } from '@angular/core';
import * as feather from 'feather-icons';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements AfterViewInit {
  currentYear: number;
  constructor(){
    this.currentYear = new Date().getFullYear();
  }

  ngAfterViewInit() {
    this.replaceFeatherIcons();
  }

  private replaceFeatherIcons() {
    if (typeof feather !== 'undefined') {
      feather.replace();
    }
  }
}