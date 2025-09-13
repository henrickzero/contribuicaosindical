import { Component, AfterViewInit, HostListener } from '@angular/core';
import * as feather from 'feather-icons';
import { GlobalService } from 'src/app/service/global.service'; 


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements AfterViewInit {

  isMenuOpen = false;
  appName: string;

  constructor(private globalService: GlobalService) {
    this.appName = 'Gerenciamento de Oposição Sindical';
    // Subscribe for dynamic updates
    this.globalService.appName$.subscribe(name => {
      this.appName = name;
    });
  }

  ngAfterViewInit() {
    this.replaceFeatherIcons();
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event) {
    const mobileMenu = document.getElementById('mobileMenu');
    const button = document.querySelector('.mobile-menu-button');
    if (mobileMenu && button && !mobileMenu.contains(event.target as Node) && !button.contains(event.target as Node)) {
      this.toggleMobileMenu(false);
    }
  }

  toggleMobileMenu(open?: boolean) {
    this.isMenuOpen = open !== undefined ? open : !this.isMenuOpen;
    const menu = document.getElementById('mobileMenu');
    const menuIcon = document.querySelector('i[data-feather="menu"]');
    const closeIcon = document.querySelector('i[data-feather="x"]');
    if (menu && menuIcon && closeIcon) {
      menu.classList.toggle('hidden', !this.isMenuOpen);
      menuIcon.classList.toggle('hidden', this.isMenuOpen);
      closeIcon.classList.toggle('hidden', !this.isMenuOpen);
      this.replaceFeatherIcons();
    }
  }

  private replaceFeatherIcons() {
    if (typeof feather !== 'undefined') {
      feather.replace();
    }
  }
}
