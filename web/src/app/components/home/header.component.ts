import { Component, AfterViewInit, HostListener, ChangeDetectorRef } from '@angular/core'; // Adicione ChangeDetectorRef se precisar forçar detect
import * as feather from 'feather-icons';
import { GlobalService } from 'src/app/service/global.service'; 

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements AfterViewInit {

  isMenuOpen = false;
  isHidden = false;
  lastScrollTop = 0;
  appName: string;

  constructor(
    private globalService: GlobalService,
    private cdr: ChangeDetectorRef // Para forçar detect changes se necessário
  ) {
    this.appName = 'Gerenciamento de Oposição Sindical';
    this.globalService.appName$.subscribe(name => {
      this.appName = name;
    });
  }

  ngAfterViewInit() {
    this.replaceFeatherIcons();
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event) {
    const mobileMenu = document.querySelector('.mobile-menu');
    const button = document.querySelector('.mobile-menu-button');
    if (mobileMenu && button && !mobileMenu.contains(event.target as Node) && !button.contains(event.target as Node)) {
      this.toggleMobileMenu(false);
    }
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    if (scrollTop > this.lastScrollTop && scrollTop > 100) {
      this.isHidden = true;
    } else if (scrollTop < this.lastScrollTop || scrollTop < 50) {
      this.isHidden = false;
    }
    this.lastScrollTop = scrollTop <= 0 ? 0 : scrollTop;
  }

  toggleMobileMenu(open?: boolean) {
    this.isMenuOpen = open !== undefined ? open : !this.isMenuOpen;
    // Força replace dos ícones após toggle
    setTimeout(() => this.replaceFeatherIcons(), 0);
    // Se precisar: this.cdr.detectChanges(); // Só se o menu não atualizar
  }

  private replaceFeatherIcons() {
    if (typeof feather !== 'undefined') {
      feather.replace({ 'stroke-width': 2, width: '24', height: '24' });
    }
  }
}