import { Component } from '@angular/core';
import { GlobalService } from 'src/app/service/global.service'; 

@Component({
  selector: 'home-header',
  templateUrl: './header.component.html',
//   styleUrls: ['./header.component.css']
})
export class HomeHeaderComponent {
  isMenuOpen = false;
  appName: string;

  constructor(private globalService: GlobalService) {
        this.appName = globalService.appName;
    // Subscribe for dynamic updates
    this.globalService.appName$.subscribe(name => {
      this.appName = name;
    });
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}