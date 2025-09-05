import { Component } from '@angular/core';
import { GlobalService } from 'src/app/service/global.service'; 

@Component({
  selector: 'app-root',
  templateUrl: './matriz.component.html',
  styleUrls: ['./matriz.component.css']
})
export class MatrizComponent {
  showModal = false;
  showTaskModal = false;

  constructor(private globalService: GlobalService) {
      globalService.setAppName("Matriz de Eisenhower")
  }

}