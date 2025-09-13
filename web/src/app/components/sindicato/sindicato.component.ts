import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SindicatoService } from 'src/app/service/sindicato.service';  // Serviço para sindicatos e empresas

@Component({
  selector: 'app-sindicato',
  templateUrl: './sindicato.component.html',
  styleUrls: ['./sindicato.component.css']
})
export class SindicatoComponent implements OnInit {
  sindicato: any = {}; // Tipo deve ser ajustado
  empresas: any[] = []; // Tipo deve ser ajustado para modelo de Empresa

  constructor(private route: ActivatedRoute, private sindicatoService: SindicatoService) {}

  ngOnInit() {
    // const id = this.route.snapshot.paramMap.get('id');
    // this.sindicatoService.getSindicatoById(id).subscribe(sindicato => {
    //   this.sindicato = sindicato;
    //   this.empresas = sindicato.empresas || []; // Assumindo que o sindicato retorna lista de empresas
    // });
  }
}